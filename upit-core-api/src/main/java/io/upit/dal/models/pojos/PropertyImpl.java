package io.upit.dal.models.pojos;

import io.upit.dal.models.Property;

public class PropertyImpl extends AbstractResource<String> implements Property {

    private String value;
    private String type;
    private String source;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
