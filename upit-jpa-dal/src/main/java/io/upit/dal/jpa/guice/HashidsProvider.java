package io.upit.dal.jpa.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import fm.jiecao.lib.Hashids;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashidsProvider implements Provider<Hashids> {
    private final static Logger logger = LoggerFactory.getLogger(HashidsProvider.class);

    private final static String SALT_KEY = "HashidsSalt";

    private final Hashids instance;

    @Inject
    public HashidsProvider(@Named("UpitConfiguration")Configuration configuration) {
        Hashids tmpInstance = null;
        try {
            String salt = configuration.getString(SALT_KEY, null);
            if(null == salt) {
                salt = RandomStringUtils.random(31, true, true);
                logger.warn("Randomly generated Hashids salt: " + salt);
                configuration.setProperty(SALT_KEY, salt);
            } else {
                logger.info("Using Hashids salt: " + salt);
            }

            tmpInstance = new Hashids(salt, 5);
        } catch(Exception e) {
            logger.error("Failed initializing  Hashids", e);
        }
        this.instance = tmpInstance;
    }

    @Override
    public Hashids get() {
        return instance;
    }

}
