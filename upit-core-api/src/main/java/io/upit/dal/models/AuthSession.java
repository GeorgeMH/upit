package io.upit.dal.models;

import org.joda.time.DateTime;

public interface AuthSession {

	public String getSessionId();
	public void setSessionId(String sessionId);

	public String getUserId();
	public void setUserId(String userId);

	public DateTime getCreated();
	public void setCreated(DateTime created);

	public DateTime getExpires();
	public void setExpires(DateTime expires);

	public DateTime getLastAccessed();

	public void setLastAccessed(DateTime lastAccessed);

	public boolean isActive();
	public void setActive(boolean active);

}
