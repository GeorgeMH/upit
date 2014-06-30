package io.upit.dal.models.pojos;

import io.upit.dal.models.AuthSession;

import java.util.Objects;

import org.joda.time.DateTime;

public class AuthSessionImpl implements AuthSession {
	
	private String sessionId;
	private String userId;
	private String token;
	private DateTime created;
	private DateTime expires;
	private DateTime lastAccessed;
	private boolean active;

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	public String getToken() {
		return token;
	}

	@Override
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public DateTime getCreated() {
		return created;
	}

	@Override
	public void setCreated(DateTime created) {
		this.created = created;
	}

	@Override
	public DateTime getExpires() {
		return expires;
	}

	@Override
	public void setExpires(DateTime expires) {
		this.expires = expires;
	}

	@Override
	public DateTime getLastAccessed() {
		return lastAccessed;
	}

	@Override
	public void setLastAccessed(DateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSessionId());
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AuthSession)){
			return false;
		}
		AuthSession check = (AuthSession)obj;
		return Objects.equals(getSessionId(), check.getSessionId());
	}


}
