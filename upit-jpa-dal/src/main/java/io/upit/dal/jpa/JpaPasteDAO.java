package io.upit.dal.jpa;

import Hashidsjava.Hashids;
import com.google.inject.Inject;
import io.upit.dal.PasteDAO;
import io.upit.dal.jpa.models.JpaPaste;
import io.upit.dal.models.Paste;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class JpaPasteDAO extends EntityManagerDAO<Paste, Long> implements PasteDAO {

    private final Hashids hashids;

    @Inject
    public JpaPasteDAO(EntityManager entityManager, Hashids hashids) {
        super(Paste.class, JpaPaste.class, entityManager);
        this.hashids = hashids;
    }

    @Transactional
    public Paste create(Paste paste) {
        paste = autoBoxForJpa(paste);

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
        } catch (NoResultException e) {
            return null;
        }
    }
}
