package io.upit.dal.models;

import org.joda.time.DateTime;

public interface AuthSession {

	public String getSessionId();
	public void setSessionId(String sessionId);

	public String getUserId();
	public void setUserId(String userId);

	public String getToken();
	public void setToken(String authToken);

	public DateTime getCreated();
	public void setCreated(DateTime created);

	public DateTime getExpires();
	public void setExpires(DateTime expires);

	public DateTime getLastAccessed();
	public DateTime setLastAccessed(DateTime lastAccessed);

	public boolean isActive();
	public void setActive(boolean active);

	public boolean isSecure();
	public void setSecure(boolean secure);

}
