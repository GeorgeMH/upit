package io.upit.dal.models.security.acls;

import io.upit.dal.models.Resource;

public interface AclEntry extends Resource<String> {

    String getResourceType();

    void setResourceType(String resourceType);

    String getResourceId();

    void setResourceId(String resourceId);

    AclType getAclType();

    void setAclType(AclType aclEntry);

    String getOwnerType();

    void setOwnerType(String ownerType);

    String getOwnerId();

    void setOwnerId(String ownerId);

}
