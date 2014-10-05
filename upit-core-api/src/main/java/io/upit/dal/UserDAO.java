package io.upit.dal;

import io.upit.dal.models.User;

public interface UserDAO extends DAO<User, String> {

    public User getByUserNameOrEmail(String input);

}