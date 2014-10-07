package io.upit.dal.models;

import java.util.Date;

public interface Paste extends Resource<Long> {

    public String getIdHash();
    public void setIdHash(String idHash);

    public String getText();
    public void setText(String text);

    public String getUserId();
    public void setUserId(String userId);
    
    public Date getCreated();
    public void setCreated(Date date);

    public String getParentId();
    public void setParentId(String parentId);

    public String getSyntaxId();
    public void setSyntaxId(String syntaxId);

}
