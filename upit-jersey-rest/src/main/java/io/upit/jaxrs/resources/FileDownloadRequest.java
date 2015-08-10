package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.AclEntryMethodAuthorizer;
import io.upit.guice.security.authorizers.AnonymousUserAuthorizer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public class FileDownloadRequest {

    private final UploadedFileResource uploadedFileResource;

    @Inject
    public FileDownloadRequest(UploadedFileResource uploadedFileResource) {
        this.uploadedFileResource = uploadedFileResource;
    }

    @GET
    @Path("{shortHash}")
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public Response get(@PathParam("shortHash") String shortHash) {
        return uploadedFileResource.download(shortHash);
    }

    @GET
    @Path("{shortHash}/{fileName}")
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public Response get(@PathParam("shortHash") String shortHash, String fileName) {
        return uploadedFileResource.download(shortHash);
    }

}
