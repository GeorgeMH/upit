package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import io.upit.dal.PasteDAO;
import io.upit.dal.models.Paste;
import io.upit.dal.models.User;
import io.upit.dal.models.pojos.PasteImpl;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("paste")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasteResource {

    private final PasteDAO pasteDAO;

    @Inject
    public PasteResource(PasteDAO pasteDAO) {
        this.pasteDAO = pasteDAO;
    }

    @POST
    public String create(Paste paste) {
        paste.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        paste.setCreated(new DateTime());
        return pasteDAO.create(paste);
    }

    @PUT
    public void update(Paste paste) {
        pasteDAO.update(paste);
    }

    @DELETE
    public void delete(Paste paste) {
        pasteDAO.delete(paste);
    }

    @GET
    @Path("{id}/")
    public Paste getUserById(@PathParam("id") String idStr) {
        //return pasteDAO.getById(idStr);
        return new PasteImpl();
    }

}
