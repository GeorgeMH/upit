package io.upit.jaxrs.resources;

import com.google.inject.Provider;
import io.upit.dal.AbstractDAO;
import io.upit.dal.models.Paste;
import io.upit.dal.models.Resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractResource<ResourceClass extends Resource<IDType>, IDType extends Serializable> {

    private final Class<ResourceClass> resourceClass;
    private final Provider<? extends AbstractDAO<ResourceClass, IDType>> dao;

    public AbstractResource(Class<ResourceClass> resourceClass, Provider<? extends AbstractDAO<ResourceClass, IDType>> dao) {
        this.resourceClass = resourceClass;
        this.dao = dao;
    }

    @POST
    public ResourceClass create(ResourceClass resource) {
        IDType id = dao.get().create(resource);
        if(null != id) {
            resource.setId(id);
        }
        return resource;
    }

    @PUT
    public void update(ResourceClass resource) {
        dao.get().update(resource);
    }

    @DELETE
    public void delete(ResourceClass resource) {
        dao.get().delete(resource);
    }

    @GET
    @Path("{id}/")
    public ResourceClass getById(@PathParam("id") IDType id) {
        return dao.get().getById(id);
    }

}
