package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.PasteDAO;
import io.upit.dal.models.Paste;
import io.upit.dal.models.User;
import io.upit.dal.models.pojos.PasteImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.UUID;

@Path("paste")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasteResource extends AbstractResource<Paste, String> {

    private final Provider<PasteDAO> pasteDAO;

    @Inject
    public PasteResource(Provider<PasteDAO> pasteDAO) {
        super(Paste.class, pasteDAO);
        this.pasteDAO = pasteDAO;
    }

    @POST
    @Override
    public Paste create(Paste paste) {
        paste.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        return super.create(paste);
    }


}
