package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import com.sun.jersey.core.header.ContentDisposition;
import io.upit.dal.UploadedFileDAO;
import io.upit.dal.jpa.models.JpaUploadedFile;
import io.upit.dal.models.UploadedFile;
import io.upit.jaxrs.exceptions.ResourceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

@Path("/uploadedFile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UploadedFileResource  extends AbstractResource<UploadedFile, Long> {
    private final Logger logger = LoggerFactory.getLogger(UploadedFileResource.class);

    private final UploadedFileDAO uploadedFileDAO;
    private final File uploadedFileRepository;

    @Inject
    public UploadedFileResource(UploadedFileDAO dao, @Named("uploadedFileRepositoryPath") File uploadedFileRepository) {
        super(UploadedFile.class, dao);
        this.uploadedFileDAO = dao;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @POST
    @Transactional
    public UploadedFile create(UploadedFile resource) {
        //TODO better error/exceptions
        throw new ResourceException("Access Denied.");
    }

    @GET
    @Path("download/{id}/{hash}")
    public Response downloadFile(@PathParam("id") final Long id, @PathParam("hash") String hash) {
        UploadedFile uploadedFile = uploadedFileDAO.getById(id);
        if(null == uploadedFile) {
            // TODO: Better response handling!
            return Response.status(404).build();
        }

        File targetFile = new File(uploadedFileRepository, uploadedFile.getHash());

        final InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(targetFile);
        } catch(FileNotFoundException e) {
            logger.error("UploadedFile not present in file repository: " + targetFile, e);
            return Response.status(404).build();
        }

        StreamingOutput streamingOutput = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                pipe(fileInputStream, output);
            }

            public void pipe(InputStream is, OutputStream os) throws IOException {
                try {
                    int n;
                    byte[] buffer = new byte[1024];
                    while ((n = is.read(buffer)) > -1) {
                        os.write(buffer, 0, n);
                    }
                } finally {
                    try {
                        os.close();
                    } catch (IOException e) {

                    }

                    try {
                        is.close();
                    } catch (IOException e) {

                    }
                }
            }

        };

        Response.ResponseBuilder response = Response.ok(streamingOutput, uploadedFile.getContentType());
        response.header("content-length", uploadedFile.getFileSize() + "");

        // TODO: Really need to do better checking on the content type to determine if its an image/video etc.
        if(null == uploadedFile.getContentType() || !uploadedFile.getContentType().startsWith("image")) {
            response.header("content-disposition", ContentDisposition.type("attachment")
                                                                     .fileName(uploadedFile.getFileName())
                                                                     .size(uploadedFile.getFileSize()));
        }
        return response.build();
    }

    @POST
    @Path("/uploadFiles")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Set<UploadedFile> uploadFiles(@Context HttpServletRequest request) {
        // TODO: Clean ths up!

        if(!ServletFileUpload.isMultipartContent(request)) {
            //TODO better error/exceptions
            throw new ResourceException("Invalid Request");
        }

        ServletFileUpload servletFileUpload = new ServletFileUpload();

        Set<UploadedFile> resultSet = new HashSet<>();

        try {
            FileItemIterator items = servletFileUpload.getItemIterator(request);
            while(items.hasNext()){
                FileItemStream item = items.next();
                if(item.isFormField()){
                    continue;
                }

                File targetFile = File.createTempFile("upit-", "uploadingFile", uploadedFileRepository);
                try {
                    Files.copy(item.openStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }catch(IOException e) {
                    targetFile.delete();
                    logger.error("Failed writing file to temporary target: " + targetFile.getAbsolutePath(), e);
                    throw new ResourceException("Failed writing file to temporary target: " + targetFile.getAbsolutePath(), e);
                }

                String fileHash = null;

                try(InputStream hashStream = new FileInputStream(targetFile)) {
                    fileHash = DigestUtils.sha256Hex(hashStream);
                } catch(IOException e) {
                    // TODO: better error/exception handling
                    targetFile.delete();
                    throw new ResourceException("Failed calculating uploaded file hash", e);
                }

                UploadedFile uploadedFile = uploadedFileDAO.getByHash(fileHash);
                if(null != uploadedFile) {
                    // The same file has already been uploaded.
                    // TODO: Should probably do some better error checking here
                    if(!targetFile.delete()){
                        logger.warn("Failed deleting temp upload file after finding duplicate hash.");
                    }
                } else {
                    uploadedFile = new JpaUploadedFile();
                    uploadedFile.setHash(fileHash);
                    uploadedFile.setFileSize(targetFile.length());
                    uploadedFile.setFileName(item.getName());

                    // TODO: Really need to do better checking on the content type to determine if its an image/video etc.
                    if(null != item.getContentType()) {
                        uploadedFile.setContentType(item.getContentType());
                    } else {
                        uploadedFile.setContentType("application/octet-stream");
                    }
                    uploadedFile = uploadedFileDAO.create(uploadedFile);

                    //TODO: this won't work across file system types, we are safe here since its all in the same directory.
                    targetFile.renameTo(new File(uploadedFileRepository, fileHash));
                }
                resultSet.add(uploadedFile);
            }
        } catch (FileUploadException|IOException e) {
            //TODO better error/exceptions
            throw new ResourceException("Failed processing file upload", e);
        }

        return resultSet;
    }
}
