package io.upit.dal;

import io.upit.dal.models.security.AuthenticationMetaData;

import java.util.List;

public interface AuthenticationMetaDataDAO extends DAO<AuthenticationMetaData, Long> {

    List<AuthenticationMetaData> getByUserId(Long id);

}
