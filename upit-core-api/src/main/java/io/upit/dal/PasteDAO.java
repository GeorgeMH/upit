package io.upit.dal;

import io.upit.dal.models.Paste;

public interface PasteDAO extends DAO<Paste, Long> {

    Paste getByIdHash(String idHash);

}
