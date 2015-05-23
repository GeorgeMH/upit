package io.upit.security.providers;


import io.upit.dal.models.security.AuthenticationMetaData;
import io.upit.dal.models.security.LoginRequest;
import io.upit.security.AuthenticationProvider;
import org.apache.commons.codec.digest.DigestUtils;

public class Sha512AuthenticationProvider implements AuthenticationProvider {

    public boolean authenticate(LoginRequest loginRequest, AuthenticationMetaData authMetaData) {
        String userSalt = getSha512Hash(authMetaData.getSalt(), loginRequest.getPassword());
        return userSalt.equals(authMetaData.getSaltedPassword());
    }

    public static String getSha512Hash(String salt, String password) {
        return DigestUtils.sha512Hex(salt + "|" + password);
    }

}
