package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.UserDAO;
import io.upit.dal.jpa.models.JpaUser;
import io.upit.dal.models.User;
import org.hashids.Hashids;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class JpaUserDAO extends EntityManagerDAO<User, JpaUser, Long> implements UserDAO {

    private final Hashids hashids;

    @Inject
    public JpaUserDAO(EntityManager entityManager, Hashids hashids) {
        super(User.class, JpaUser.class, entityManager);
        this.hashids = hashids;
    }

    @Override
    @Transactional
    public User create(User user){
        User ret = super.create(user);
        ret.setIdHash(hashids.encode(ret.getId()));
        update(ret);
        return ret;
    }

    @Override
    @Transactional
    public User getByUserNameOrEmail(String input) {
        TypedQuery<JpaUser> query = entityManager.createQuery("SELECT user FROM User user WHERE lower(user.userName) = :input OR user.email = :input", JpaUser.class);
        query.setParameter("input", input.toLowerCase());
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getByIdHash(String idHash) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.idHash = :idHash", User.class);
        query.setParameter("idHash", idHash);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
