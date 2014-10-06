package io.upit.dal.jpa;

        import com.google.inject.Inject;
        import io.upit.dal.UploadedFileDAO;
        import io.upit.dal.jpa.models.JpaUploadedFile;
        import io.upit.dal.models.UploadedFile;
        import org.hibernate.Criteria;

        import javax.persistence.*;
        import javax.persistence.criteria.CriteriaQuery;
        import javax.persistence.criteria.Root;

public class JpaUploadedFileDAO  extends EntityManagerDAO<UploadedFile, Long> implements UploadedFileDAO {

    @Inject
    public JpaUploadedFileDAO(EntityManager entityManager) {
        super(JpaUploadedFile.class, entityManager);
    }

    public UploadedFile getByHash(String fileHash) {
        JpaUploadedFile foo = null;
        TypedQuery<JpaUploadedFile> query = entityManager.createQuery("SELECT uf FROM UploadedFile uf WHERE uf.hash = :hash", JpaUploadedFile.class);
        query.setParameter("hash", fileHash);

        try {
            return query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

}
