package io.upit.jaxrs.resources;

import com.google.inject.persist.Transactional;
import io.upit.UpitServiceException;
import io.upit.dal.models.Resource;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.AclEntryMethodAuthorizer;
import io.upit.jaxrs.exceptions.ResourceException;
import io.upit.services.AbstractResourceService;

import javax.ws.rs.*;
import java.io.Serializable;

public abstract class AbstractResource<ResourceClass extends Resource<IDType>, IDType extends Serializable> {

    private final Class<ResourceClass> resourceClass;
    private final AbstractResourceService<ResourceClass, IDType> resourceService;

    public AbstractResource(Class<ResourceClass> resourceClass, AbstractResourceService<ResourceClass, IDType> resourceService) {
        this.resourceClass = resourceClass;
        this.resourceService = resourceService;
    }

    @POST
    @Transactional
    @PreAuthorize
    public ResourceClass create(ResourceClass resource) {
        try {
            return resourceService.create(resource);
        } catch (UpitServiceException e) {
            throw new ResourceException("Failed creating resource " + resourceClass.getName(), e);
        }
    }

    @PUT
    @Transactional
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass update(ResourceClass resource) {
        return resourceService.update(resource);
    }

    @DELETE
    @Transactional
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass delete(ResourceClass resource) {
        return resourceService.delete(resource);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass deleteById(@PathParam("id") IDType id) {
        return resourceService.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Transactional
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass getById(@PathParam("id") IDType id) {
        return resourceService.getById(id);
    }

}
