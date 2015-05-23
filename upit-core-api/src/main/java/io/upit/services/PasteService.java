package io.upit.services;

import com.google.inject.Inject;
import io.upit.dal.PasteDAO;
import io.upit.dal.models.Paste;

public class PasteService extends AbstractResourceService<Paste, Long> {

    private final PasteDAO pasteDAO;

    @Inject
    public PasteService(PasteDAO pasteDAO) {
        super(Paste.class, pasteDAO);
        this.pasteDAO = pasteDAO;
    }

    public Paste getByIdHash(String hash) {
        return pasteDAO.getByIdHash(hash);
    }

}
