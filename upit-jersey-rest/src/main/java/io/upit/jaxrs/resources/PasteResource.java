package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import io.upit.dal.PasteDAO;
import io.upit.dal.models.Paste;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("paste")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasteResource extends AbstractResource<Paste, Long> {

    private final PasteDAO pasteDAO;

    @Inject
    public PasteResource(PasteDAO pasteDAO) {
        super(Paste.class, pasteDAO);
        this.pasteDAO = pasteDAO;
    }

    @GET
    @Path("/hash/{idHash}")
    public Paste getByIdHash(@PathParam("idHash") String hash) {
        return pasteDAO.getByIdHash(hash);
    }

}
