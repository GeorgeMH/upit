package io.upit.dal.jpa.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import Hashidsjava.Hashids;
import io.upit.dal.*;
import io.upit.dal.jpa.*;
import io.upit.dal.jpa.models.*;
import io.upit.dal.jpa.models.security.JpaAuthenticationMetaData;
import io.upit.dal.models.*;
import io.upit.dal.models.security.AuthenticationMetaData;

public class UpitJpaModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new JpaPersistModule("upit-dal-hibernate"));

        bind(Hashids.class).toProvider(HashidsProvider.class).in(Singleton.class);

        bind(User.class).to(JpaUser.class);
        bind(UserDAO.class).to(JpaUserDAO.class);

        bind(AuthSession.class).to(JpaAuthSession.class);
        bind(AuthSessionDAO.class).to(JpaAuthSessionDAO.class);

        bind(Paste.class).to(JpaPaste.class);
        bind(PasteDAO.class).to(JpaPasteDAO.class);

        bind(UploadedFile.class).to(JpaUploadedFile.class);
        bind(UploadedFileDAO.class).to(JpaUploadedFileDAO.class);

        bind(PropertyValue.class).to(JpaPropertyValue.class);
        bind(PropertyDAO.class).to(JpaPropertyDAO.class);

        bind(AuthenticationMetaData.class).to(JpaAuthenticationMetaData.class);
        bind(AuthenticationMetaDataDAO.class).to(JpaAuthenticationMetaDataDAO.class);
    }

}
