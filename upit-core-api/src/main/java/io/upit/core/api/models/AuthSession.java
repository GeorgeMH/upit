package io.upit.core.api.models;

import java.util.Date;

public interface AuthSession {

	public String getSessionId();
	public void setSessionId(String sessionId);

	public String getUserId();
	public void setUserId(String userId);

	public String getAuthToken();
	public void setAuthToken(String authToken);

	public Date getCreated();
	public void setCreated(Date created);

	public Date getExpires();
	public void setExpires(Date expires);

	public Date getLastAccessed();
	public void setLastAccessed(Date lastAccessed);

	public boolean isActive();
	public void setActive(boolean active);

	public boolean isSecure();
	public void setSecure(boolean secure);

}
