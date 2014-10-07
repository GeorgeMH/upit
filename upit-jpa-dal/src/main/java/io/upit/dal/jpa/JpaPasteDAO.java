package io.upit.dal.jpa;

import com.google.inject.Inject;
import fm.jiecao.lib.Hashids;
import io.upit.dal.PasteDAO;
import io.upit.dal.jpa.models.JpaPaste;
import io.upit.dal.jpa.models.JpaUploadedFile;
import io.upit.dal.models.Paste;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class JpaPasteDAO extends EntityManagerDAO<Paste, Long> implements PasteDAO {

    private final Hashids hashids;

    @Inject
    public JpaPasteDAO(EntityManager entityManager, Hashids hashids) {
        super(JpaPaste.class, entityManager);
        this.hashids = hashids;
    }

    public Paste create(Paste paste){
        Paste ret = super.create(paste);
        ret.setIdHash(hashids.encode(paste.getId()));
        return update(ret);
    }

    @Override
    public Paste getByIdHash(String idHash) {
        TypedQuery<JpaPaste> query = entityManager.createQuery("SELECT uf FROM Paste uf WHERE uf.idHash = :idHash", JpaPaste.class);
        query.setParameter("idHash", idHash);

        try {
            return query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
