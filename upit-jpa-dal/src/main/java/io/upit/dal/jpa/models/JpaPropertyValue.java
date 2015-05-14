package io.upit.dal.jpa.models;

import io.upit.dal.models.PropertyValue;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class JpaPropertyValue implements PropertyValue {

    @Id
    private String id;

    @Column(nullable = false)
    private int version;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = true)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String value;

    @Column(nullable = true)
    private String source;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

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
