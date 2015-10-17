package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.sun.jersey.core.header.ContentDisposition;
import io.upit.UpitServiceException;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.UploadedFile;
import io.upit.dal.models.pojos.UploadedFileImpl;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.AclEntryMethodAuthorizer;
import io.upit.guice.security.authorizers.DenyAllMethodAuthorizer;
import io.upit.jaxrs.exceptions.ResourceException;
import io.upit.jaxrs.guice.providers.AuthSessionProvider;
import io.upit.services.UploadedFileService;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/uploadedFile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UploadedFileResource extends AbstractResource<UploadedFile, Long> {
    private final Logger logger = LoggerFactory.getLogger(UploadedFileResource.class);

    private final UploadedFileService uploadedFileService;
    private final AuthSessionProvider authSessionProvider;

    @Inject
    public UploadedFileResource(UploadedFileService dao, AuthSessionProvider authSessionProvider) {
        super(UploadedFile.class, dao);
        this.uploadedFileService = dao;
        this.authSessionProvider = authSessionProvider;
    }

    @POST
    @PreAuthorize(methodAuthorizers = {DenyAllMethodAuthorizer.class})
    public UploadedFile create(UploadedFile resource) {
        throw new ResourceException("Access Denied.");
    }

    @GET
    @Path("hash/{shortHash}")
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public UploadedFile getByIdHash(@PathParam("shortHash") String shortHash) {
        return uploadedFileService.getByIdHash(shortHash);
    }

    @GET
    @Path("user/{userId}")
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public List<? extends UploadedFile> getFilesByUserId(@PathParam("userId")int userId) {
        return uploadedFileService.getFilesByUserId(userId);
    }

    @GET
    @Path("download/{shortHash}")
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public Response download(@PathParam("shortHash") String shortHash) {
        int dotIdx = shortHash.indexOf('.');
        if (dotIdx > 0) {
            shortHash = shortHash.substring(0, dotIdx);
        }

        final UploadedFile uploadedFile = uploadedFileService.getByIdHash(shortHash);
        if (null == uploadedFile) {
            return Response.status(404).build();
        }

        try (final InputStream fileInputStream = uploadedFileService.getFileStream(uploadedFile); ){

            if (null == fileInputStream) {
                return Response.status(404).build();
            }

            StreamingOutput streamingOutput = (OutputStream output) -> IOUtils.copy(fileInputStream, output);

            Response.ResponseBuilder response = Response.ok(streamingOutput, uploadedFile.getContentType());
            response.header("content-length", uploadedFile.getFileSize() + "");

            if (null == uploadedFile.getContentType() || !uploadedFile.getContentType().startsWith("image")) {
                response.header("content-disposition", ContentDisposition.type("attachment")
                    .fileName(uploadedFile.getFileName())
                    .size(uploadedFile.getFileSize()));
            }
            return response.build();
        } catch (IOException | UpitServiceException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @PreAuthorize
    public Set<UploadedFile> uploadFiles(@Context HttpServletRequest request) {
        // TODO: Clean ths up!

        if (!ServletFileUpload.isMultipartContent(request)) {
            //TODO better error/exceptions
            throw new ResourceException("Invalid Request");
        }

        ServletFileUpload servletFileUpload = new ServletFileUpload();

        Set<UploadedFile> resultSet = new HashSet<>();

        AuthSession authSession = authSessionProvider.get();
        Long userId = null == authSession ? null  : authSession.getUserId();

        try {
            FileItemIterator items = servletFileUpload.getItemIterator(request);
            while (items.hasNext()) {
                FileItemStream item = items.next();
                if (item.isFormField()) {
                    continue;
                }

                UploadedFile uploadedFile = new UploadedFileImpl();
                uploadedFile.setFileName(item.getName());

                if (null != item.getContentType() && item.getContentType().length() > 0) {
                    uploadedFile.setContentType(item.getContentType());
                }

                UploadedFile parsedUploadedFile = uploadedFileService.uploadFile(uploadedFile, item.openStream());
                parsedUploadedFile.setUserId(userId);

                resultSet.add(parsedUploadedFile);
            }
        } catch (FileUploadException | IOException | UpitServiceException e) {
            //TODO better error/exceptions
            throw new ResourceException("Failed processing file upload", e);
        }

        return resultSet;
    }
}
