package io.upit.dal.models.pojos.security.acls;

import io.upit.dal.models.security.acls.AclEntry;
import io.upit.dal.models.security.acls.AclType;

import java.util.Date;

public class AclEntryImpl implements AclEntry {

    private String id;
    private Date created;
    private int version;
    private String resourceType;
    private String resourceId;
    private AclType aclType;
    private String ownerType;
    private String ownerId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public Date getCreated() {
        return this.created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String getResourceType() {
        return resourceType;
    }

    @Override
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String getResourceId() {
        return resourceId;
    }

    @Override
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public AclType getAclType() {
        return aclType;
    }

    @Override
    public void setAclType(AclType aclType) {
        this.aclType = aclType;
    }

    @Override
    public String getOwnerType() {
        return ownerType;
    }

    @Override
    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    @Override
    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

}
