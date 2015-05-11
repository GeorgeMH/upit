package io.upit.dal.models;

import java.util.Date;

public interface Paste extends Resource<Long> {

    String getIdHash();
    void setIdHash(String idHash);

    String getText();
    void setText(String text);

    String getUserId();
    void setUserId(String userId);
    
    Date getCreated();
    void setCreated(Date date);

    String getParentId();
    void setParentId(String parentId);

    String getSyntaxId();
    void setSyntaxId(String syntaxId);

}
