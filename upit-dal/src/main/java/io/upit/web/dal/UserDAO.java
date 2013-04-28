package io.upit.web.dal;

import io.upit.web.dal.models.User;

import org.bson.types.ObjectId;

import com.google.inject.Singleton;

@Singleton
public interface UserDAO extends DAO<User, ObjectId> {

}
