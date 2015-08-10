package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import io.upit.dal.models.Paste;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.AclEntryMethodAuthorizer;
import io.upit.services.PasteService;

import javax.ws.rs.*;
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
