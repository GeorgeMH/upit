package io.upit.dal.jpa.models;

import io.upit.dal.models.Paste;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class JpaPaste implements Paste {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Version
    private int version;

    @Lob
    private String text;

    private String userId;

    private Date created;

    private String parentId;

    private String syntaxId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getVersion(){
        return version;
    }

    @Override
    public void setVersion(int version){
        this.version = version;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getSyntaxId() {
        return syntaxId;
    }

    @Override
    public void setSyntaxId(String syntaxId) {
        this.syntaxId = syntaxId;
    }
}
