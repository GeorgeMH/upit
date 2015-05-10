package io.upit.services;

import Hashidsjava.Hashids;
import com.google.inject.Inject;
import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

public class UserService extends AbstractResourceService<User, Long> {

    private final UserDAO userDAO;
    private final Hashids hashIds;

    @Inject
    public UserService(UserDAO userDAO, Hashids hashIds) {
        super(User.class, userDAO);
        this.userDAO = userDAO;
        this.hashIds = hashIds;
    }

    public User getByIdHash(String shortHash) {
        return getById(hashIds.decode(shortHash)[0]);
    }


    public User getByUserNameOrEmail(String input) {
        return userDAO.getByUserNameOrEmail(input);
    }

    public User update(User userToUpdate) {
        User persistedUser = userDAO.getById(userToUpdate.getId());
        persistedUser.setIdHash(hashIds.encode(persistedUser.getId()));

        return this.userDAO.update(userToUpdate);
    }


}
