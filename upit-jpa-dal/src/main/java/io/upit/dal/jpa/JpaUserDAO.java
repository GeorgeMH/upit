package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.UserDAO;
import io.upit.dal.jpa.models.JpaUser;
import io.upit.dal.models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class JpaUserDAO extends EntityManagerDAO<User, Long> implements UserDAO  {

    @Inject
    public JpaUserDAO(EntityManager entityManager) {
        super(JpaUser.class, entityManager);
    }

    @Override
    @Transactional
    public User getByUserNameOrEmail(String input) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE lower(user.userName) = :input OR user.email = :input", User.class);
        query.setParameter("input", input.toLowerCase());
        return query.getSingleResult();
    }

    public User getByIdHash(String idHash) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.idHash = :idHash", User.class);
        query.setParameter("idHash", idHash);
        return query.getSingleResult();
    }

}
