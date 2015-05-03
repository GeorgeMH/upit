package io.upit.dal.jpa.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import fm.jiecao.lib.Hashids;
import io.upit.dal.*;
import io.upit.dal.jpa.*;
import io.upit.dal.jpa.models.*;
import io.upit.dal.models.*;

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

        bind(Property.class).to(JpaProperty.class);
        bind(PropertyDAO.class).to(JpaPropertyDAO.class);
    }

}
