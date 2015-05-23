package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.jpa.models.security.JpaAclEntry;
import io.upit.dal.models.security.acls.AclEntry;

import javax.persistence.EntityManager;

public class JpaAclEntryDAO extends EntityManagerDAO<AclEntry, JpaAclEntry, String> {

    @Inject
    public JpaAclEntryDAO(EntityManager entityManager) {
        super(AclEntry.class, JpaAclEntry.class, entityManager);
    }

}
