package io.upit.services;

import com.google.inject.Inject;
import io.upit.dal.AclEntryDAO;
import io.upit.dal.models.security.acls.AclEntry;

public class AclEntryService  extends AbstractResourceService<AclEntry, String> {

    private final AclEntryDAO aclEntryDAO;

    @Inject
    public AclEntryService(AclEntryDAO aclEntryDAO) {
        super(AclEntry.class, aclEntryDAO);
        this.aclEntryDAO = aclEntryDAO;
    }


}

