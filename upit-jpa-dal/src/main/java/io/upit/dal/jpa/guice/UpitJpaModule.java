package io.upit.dal.jpa.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import io.upit.dal.AuthSessionDAO;
import io.upit.dal.PasteDAO;
import io.upit.dal.UploadedFileDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.jpa.JpaAuthSessionDAO;
import io.upit.dal.jpa.JpaPasteDAO;
import io.upit.dal.jpa.JpaUploadedFileDAO;
import io.upit.dal.jpa.JpaUserDAO;
import io.upit.dal.jpa.models.JpaAuthSession;
import io.upit.dal.jpa.models.JpaPaste;
import io.upit.dal.jpa.models.JpaUploadedFile;
import io.upit.dal.jpa.models.JpaUser;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.Paste;
import io.upit.dal.models.UploadedFile;
import io.upit.dal.models.User;

public class UpitJpaModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new JpaPersistModule("upit-dal-hibernate"));

        bind(User.class).to(JpaUser.class);
        bind(UserDAO.class).to(JpaUserDAO.class);

        bind(AuthSession.class).to(JpaAuthSession.class);
        bind(AuthSessionDAO.class).to(JpaAuthSessionDAO.class);

        bind(Paste.class).to(JpaPaste.class);
        bind(PasteDAO.class).to(JpaPasteDAO.class);

        bind(UploadedFile.class).to(JpaUploadedFile.class);
        bind(UploadedFileDAO.class).to(JpaUploadedFileDAO.class);
    }


}
