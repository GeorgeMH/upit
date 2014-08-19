package io.upit.dal.jdbi.mysql.guice;

import java.io.InputStream;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;

public class PropertiesConfigurationProvider implements Provider<PropertiesConfiguration> {
	private final static Logger logger = LoggerFactory.getLogger(PropertiesConfigurationProvider.class);

	@Override
	public PropertiesConfiguration get() {
		PropertiesConfiguration configuration = new PropertiesConfiguration();

		InputStream propertiesFileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("");
		if (null == propertiesFileInputStream) {
			propertiesFileInputStream = getClass().getResourceAsStream("");
		}

		try {
			configuration.load(propertiesFileInputStream);
		} catch (ConfigurationException e) {
			logger.warn("", e);
		}

		return configuration;
	}

}
