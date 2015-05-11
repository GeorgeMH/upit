package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.AuthSessionDAO;
import io.upit.dal.jpa.models.JpaAuthSession;
import io.upit.dal.models.AuthSession;

import javax.persistence.EntityManager;

public class JpaAuthSessionDAO extends EntityManagerDAO<AuthSession, String> implements AuthSessionDAO {

    @Inject
    public JpaAuthSessionDAO(EntityManager entityManager) {
        super(AuthSession.class, JpaAuthSession.class, entityManager);
    }
}
