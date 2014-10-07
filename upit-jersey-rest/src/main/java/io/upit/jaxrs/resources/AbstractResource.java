package io.upit.jaxrs.resources;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import io.upit.dal.DAO;
import io.upit.dal.UpitDAOException;
import io.upit.dal.models.Resource;
import io.upit.jaxrs.exceptions.ResourceException;

import javax.ws.rs.*;
import java.io.Serializable;

public abstract class AbstractResource<ResourceClass extends Resource<IDType>, IDType extends Serializable> {

    private final Class<ResourceClass> resourceClass;
    private final DAO<ResourceClass, IDType> dao;

    public AbstractResource(Class<ResourceClass> resourceClass, DAO<ResourceClass, IDType> dao) {
        this.resourceClass = resourceClass;
        this.dao = dao;
    }

    @POST
    @Transactional
    public ResourceClass create(ResourceClass resource) {
        return dao.create(resource);
    }

    @PUT
    @Transactional
    public ResourceClass update(ResourceClass resource) {
        return dao.update(resource);
    }

    @DELETE
    @Transactional
    public ResourceClass delete(ResourceClass resource) {
        return dao.delete(resource);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public ResourceClass deleteById(@PathParam("id") IDType id){
        return dao.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Transactional
    public ResourceClass getById(@PathParam("id") IDType id) {
        return dao.getById(id);
    }

}
