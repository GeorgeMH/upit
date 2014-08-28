package io.upit.dal.models;

import org.joda.time.DateTime;

public interface Paste {
    
    public String getId();
    public void setId(String id);

    public String getText();
    public void setText(String text);

    public String getUserId();
    public void setUserId(String userId);
    
    public DateTime getDateCreated();
    public void setDateCreated(DateTime date);

    public String getParentId();
    public void setParentId(String parentId);

    public String getSyntaxId();
    public void setSyntaxId(String syntaxId);
}
