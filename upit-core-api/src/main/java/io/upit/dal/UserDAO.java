package io.upit.dal;

import io.upit.dal.models.User;

public interface UserDAO extends DAO<User, String> {

    User getByUserNameOrEmail(String input);

    User getByIdHash(String shortHash);
}