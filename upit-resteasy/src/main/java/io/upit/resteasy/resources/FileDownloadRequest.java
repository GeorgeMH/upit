package io.upit.resteasy.resources;

import com.google.inject.Inject;
import io.upit.resteasy.guice.security.PreAuthorize;
import io.upit.resteasy.guice.security.authorizers.AclEntryMethodAuthorizer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public class FileDownloadRequest {

    @Inject
    UploadedFileResource uploadedFileResource;

    public FileDownloadRequest() {

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
