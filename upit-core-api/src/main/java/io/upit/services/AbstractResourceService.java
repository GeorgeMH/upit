package io.upit.services;

import io.upit.UpitServiceException;
import io.upit.dal.DAO;
import io.upit.dal.models.Resource;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.AclEntryMethodAuthorizer;

import java.io.Serializable;

public class AbstractResourceService<ResourceClass extends Resource<IDType>, IDType extends Serializable> {

    private final Class<ResourceClass> resourceClass;
    private final DAO<ResourceClass, IDType> resourceClassDAO;

    public AbstractResourceService(Class<ResourceClass> resourceClass, DAO<ResourceClass, IDType> resourceClassDAO) {
        this.resourceClass = resourceClass;
        this.resourceClassDAO = resourceClassDAO;
    }

    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass create(ResourceClass resource) throws UpitServiceException {
        return resourceClassDAO.create(resource);
    }

    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass update(ResourceClass resource) {
        return resourceClassDAO.update(resource);
    }

    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass delete(ResourceClass resource) {
        return resourceClassDAO.delete(resource);
    }

    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass deleteById(IDType id) {
        return resourceClassDAO.deleteById(id);
    }

    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public ResourceClass getById(IDType id) {
        return resourceClassDAO.getById(id);
    }

}
