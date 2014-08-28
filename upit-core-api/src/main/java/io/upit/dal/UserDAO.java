package io.upit.dal;

import io.upit.dal.models.User;

public interface UserDAO extends AbstractDAO<User, String> {

    public User getByUserNameOrEmail(String input);

}