package io.upit.dal.io.upit.configuration;

import org.apache.commons.configuration.AbstractConfiguration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class UpitConfiguration extends AbstractConfiguration{

    private final Properties properties;

    public UpitConfiguration() {
        properties = new Properties();
    }

    public Properties getProperties() {
        return properties;
    }

    public void mergeProperties(Properties toMerge) {
        properties.putAll(toMerge);
    }

    @Override
    protected void addPropertyDirect(String s, Object o) {
        properties.put(s, o);
    }

    @Override
    public boolean isEmpty() {
        return properties.isEmpty();
    }

    @Override
    public boolean containsKey(String s) {
        return properties.containsKey(s);
    }

    @Override
    public Object getProperty(String s) {
        return properties.get(s);
    }

    @Override
    public Iterator<String> getKeys() {
        return properties.stringPropertyNames().iterator();
    }
}
