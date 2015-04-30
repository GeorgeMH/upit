package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.UserDAO;
import io.upit.dal.jpa.models.JpaUser;
import io.upit.dal.models.User;
import org.apache.commons.lang3.NotImplementedException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


public class JpaUserDAO extends EntityManagerDAO<User, Long> implements UserDAO  {

    @Inject
    public JpaUserDAO(EntityManager entityManager) {
        super(JpaUser.class, entityManager);
    }

    @Override
    @Transactional
    public User getByUserNameOrEmail(String input) {
        // TODO
        throw new NotImplementedException("Not implemented.");
    }

    public User getByIdHash(String shortHash) {
        // TODO
        throw new NotImplementedException("Not implemented.");
    }

}
