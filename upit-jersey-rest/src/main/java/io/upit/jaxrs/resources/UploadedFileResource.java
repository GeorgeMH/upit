package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import com.sun.jersey.core.header.ContentDisposition;
import io.upit.dal.UpitDAOException;
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

    @Inject
    public UploadedFileResource(UploadedFileDAO dao) {
        super(UploadedFile.class, dao);
        this.uploadedFileDAO = dao;
    }

    @POST
    @Transactional
    public UploadedFile create(UploadedFile resource) {
        //TODO better error/exceptions
        throw new ResourceException("Access Denied.");
    }

    @GET
    @Path("download/{shortHash}")
    public Response download(@PathParam("shortHash") String shortHash) {
        int dotIdx = shortHash.indexOf('.');
        if(dotIdx > 0){
            shortHash = shortHash.substring(0, dotIdx);
        }


        UploadedFile uploadedFile = uploadedFileDAO.getByShortHash(shortHash);
        if(null == uploadedFile) {
            return Response.status(404).build();
        }

        final InputStream fileInputStream = uploadedFileDAO.getFileStream(uploadedFile);
        if(null == fileInputStream){
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
    @Path("/upload")
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

                JpaUploadedFile uploadedFile = new JpaUploadedFile();
                uploadedFile.setFileName(item.getName());
                if(null != item.getContentType() && item.getContentType().length() > 0) {
                    uploadedFile.setContentType(item.getContentType());
                }

                UploadedFile parsedUploadedFile = uploadedFileDAO.create(uploadedFile, item.openStream());
                resultSet.add(uploadedFile);
            }
        } catch (FileUploadException|IOException|UpitDAOException e) {
            //TODO better error/exceptions
            throw new ResourceException("Failed processing file upload", e);
        }

        return resultSet;
    }
}
