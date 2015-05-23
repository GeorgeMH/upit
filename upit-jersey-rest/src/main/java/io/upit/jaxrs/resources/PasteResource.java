package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import io.upit.UpitServiceException;
import io.upit.dal.models.Paste;
import io.upit.guice.security.PreAuthorize;
import io.upit.jaxrs.exceptions.ResourceException;
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


    @POST
    @Transactional
    @PreAuthorize
    public Paste create(Paste resource) {
        try {
            return pasteService.create(resource);
        } catch (UpitServiceException e) {
            throw new ResourceException("Failed creating resource " + PasteResource.class.getName(), e);
        }
    }

    @PUT
    @Transactional
    @PreAuthorize
    public Paste update(Paste resource) {
        return super.update(resource);
    }

    @DELETE
    @Transactional
    @PreAuthorize
    public Paste delete(Paste resource) {
        return super.delete(resource);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @PreAuthorize
    public Paste deleteById(@PathParam("id") Long id) {
        return super.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Transactional
    @PreAuthorize
    public Paste getById(@PathParam("id") Long id) {
        return super.getById(id);
    }


    @GET
    @Path("/hash/{idHash}")
    @PreAuthorize
    public Paste getByIdHash(@PathParam("idHash") String hash) {
        return pasteService.getByIdHash(hash);
    }

}
