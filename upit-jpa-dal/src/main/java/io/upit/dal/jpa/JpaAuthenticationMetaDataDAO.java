package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.jpa.models.security.JpaAuthenticationMetaData;
import io.upit.dal.models.security.AuthenticationMetaData;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaAuthenticationMetaDataDAO extends EntityManagerDAO<AuthenticationMetaData, Long> implements AuthenticationMetaDataDAO {

    @Inject
    public JpaAuthenticationMetaDataDAO (EntityManager entityManager) {
        super(JpaAuthenticationMetaData.class, entityManager);
    }

    public List<JpaAuthenticationMetaData> getAuthenticationMetaDataByUserId(Long id) {
        TypedQuery<JpaAuthenticationMetaData> query = entityManager.createQuery("SELECT * FROM AuthenticationMetaData WHERE userId = :userId", JpaAuthenticationMetaData.class);
        query.setParameter("userId", id);
        return query.getResultList();
    }

}
