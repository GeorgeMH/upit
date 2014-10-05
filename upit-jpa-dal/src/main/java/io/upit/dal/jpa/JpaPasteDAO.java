package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.PasteDAO;
import io.upit.dal.jpa.models.JpaPaste;
import io.upit.dal.models.Paste;

import javax.persistence.EntityManager;

public class JpaPasteDAO extends EntityManagerDAO<Paste, String> implements PasteDAO {

    @Inject
    public JpaPasteDAO(EntityManager entityManager) {
        super(JpaPaste.class, entityManager);
    }


}
