package io.upit.dal.jpa.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import io.upit.dal.AclEntryDAO;
import io.upit.dal.AuthSessionDAO;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.PasteDAO;
import io.upit.dal.PropertyDAO;
import io.upit.dal.UploadedFileDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.jpa.JpaAclEntryDAO;
import io.upit.dal.jpa.JpaAuthSessionDAO;
import io.upit.dal.jpa.JpaAuthenticationMetaDataDAO;
import io.upit.dal.jpa.JpaPasteDAO;
import io.upit.dal.jpa.JpaPropertyDAO;
import io.upit.dal.jpa.JpaUploadedFileDAO;
import io.upit.dal.jpa.JpaUserDAO;
import io.upit.dal.jpa.models.JpaAuthSession;
import io.upit.dal.jpa.models.JpaPaste;
import io.upit.dal.jpa.models.JpaPropertyValue;
import io.upit.dal.jpa.models.JpaUploadedFile;
import io.upit.dal.jpa.models.JpaUser;
import io.upit.dal.jpa.models.security.JpaAclEntry;
import io.upit.dal.jpa.models.security.JpaAuthenticationMetaData;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.Paste;
import io.upit.dal.models.PropertyValue;
import io.upit.dal.models.UploadedFile;
import io.upit.dal.models.User;
import io.upit.dal.models.security.AuthenticationMetaData;
import io.upit.dal.models.security.acls.AclEntry;

public class UpitJpaModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new JpaPersistModule("upit-dal-hibernate"));
        bind(JPAInitializer.class).asEagerSingleton(); // Explicitly start the PersistService for us

        bind(AclEntry.class).to(JpaAclEntry.class);
        bind(AclEntryDAO.class).to(JpaAclEntryDAO.class);

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

        bind(AuthSession.class).to(JpaAuthSession.class);
        bind(AuthSessionDAO.class).to(JpaAuthSessionDAO.class);
    }

}
