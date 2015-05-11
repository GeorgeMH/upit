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
        super(AuthenticationMetaData.class, JpaAuthenticationMetaData.class, entityManager);
    }

    @Override
    public List<AuthenticationMetaData> getByUserId(Long id) {
        TypedQuery<JpaAuthenticationMetaData> query = entityManager.createQuery("SELECT * FROM AuthenticationMetaData WHERE userId = :userId", JpaAuthenticationMetaData.class);
        query.setParameter("userId", id);
        //TOdo: Note, the following does not compile in our current class hiearchy, so we usenon generic lists for now :(
        //List<JpaAuthenticationMetaData> ret = query.getResultList();
        //return (List<AuthenticationMetaData>)ret;

        List ret = query.getResultList();
        return ret;
    }

}
