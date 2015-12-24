package io.upit.resteasy.resources;

import com.google.inject.Inject;
import io.upit.dal.models.Paste;
import io.upit.resteasy.guice.security.PreAuthorize;
import io.upit.resteasy.guice.security.authorizers.AclEntryMethodAuthorizer;
import io.upit.services.PasteService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("paste")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasteResource extends AbstractResource<Paste, Long> {

    private final PasteService pasteService;

    @Inject
    public PasteResource(PasteService pasteService) {
        super(Paste.class, pasteService);
        this.pasteService = pasteService;
    }

    @GET
    @Path("/hash/{idHash}")
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public Paste getByIdHash(@PathParam("idHash") String hash) {
        return pasteService.getByIdHash(hash);
    }
}
