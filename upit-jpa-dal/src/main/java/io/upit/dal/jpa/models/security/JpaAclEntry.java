package io.upit.dal.jpa.models.security;

import io.upit.dal.models.security.acls.AclEntry;
import io.upit.dal.models.security.acls.AclType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

public class JpaAclEntry implements AclEntry {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Version
    private int version;

    @Column
    private Date created;

    @Column
    private String resourceType;

    @Column
    private String resourceId;

    @Column
    private AclType aclType;

    @Column
    private String ownerType;

    @Column
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
        return this.version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public Date getCreated() {
        return created;
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
