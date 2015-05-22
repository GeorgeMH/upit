package io.upit.dal.models.pojos;

import io.upit.dal.models.Paste;

import java.util.Objects;

public class PasteImpl extends AbstractResource<Long> implements Paste {

    private String idHash;
    private String text;
    private String userId;
    private String parentId;
    private String syntaxId;

    @Override
    public String getIdHash() {
        return idHash;
    }

    @Override
    public void setIdHash(String idHash) {
        this.idHash = idHash;
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

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Paste)) {
            return false;
        }
        return Objects.equals(getId(), ((Paste) obj).getId());
    }
}
