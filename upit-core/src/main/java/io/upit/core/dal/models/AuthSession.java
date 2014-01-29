package io.upit.core.dal.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthSession {

	@Id
	@Column(nullable = false, unique = true)
	private String sessionId;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private String authToken;

	@Column(nullable = false)
	private Date created;

	@Column(nullable = false)
	private Date expires;

	@Column(nullable = false)
	private Date lastAccessed;

	@Column(nullable = false)
	private boolean active;

	@Column(nullable = false)
	private boolean secure;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public Date getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSecure() {
		return secure;
	}

	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof AuthSession)) {
			return false;
		}
		AuthSession check = (AuthSession) obj;
		// Identity based equality
		return Objects.equals(getSessionId(), check.getSessionId());
	}

	@Override
	public int hashCode() {
		// Identity based equality
		return null == getSessionId() ? -1 : getSessionId().hashCode();
	}
}
