package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import io.upit.dal.PasteDAO;
import io.upit.dal.models.Paste;
import io.upit.services.PasteService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("paste")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasteResource extends AbstractResource<Paste, Long> {

    private final PasteService pasteService;

    @Inject
    public PasteResource(pasteService pasteServiceDao) {
        super(Paste.class, pasteService);
        this.pasteService = pasteService;
    }

    @GET
    @Path("/hash/{idHash}")
    public Paste getByIdHash(@PathParam("idHash") String hash) {
        return pasteDAO.getByIdHash(hash);
    }

}
