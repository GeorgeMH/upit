package io.upit.core.jpa.api.dal.models;

import io.upit.core.api.models.AuthSession;
import io.upit.core.api.models.User;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "AuthSession")
public class JpaAuthSession implements AuthSession {

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private JpaUser user;

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
	public String getAuthToken() {
		return authToken;
	}

	@Override
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
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
	public Date getExpires() {
		return expires;
	}

	@Override
	public void setExpires(Date expires) {
		this.expires = expires;
	}

	@Override
	public Date getLastAccessed() {
		return lastAccessed;
	}

	@Override
	public void setLastAccessed(Date lastAccessed) {
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
	public boolean isSecure() {
		return secure;
	}

	@Override
	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = JpaUser.toJpaUser(user);
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof JpaAuthSession)) {
			return false;
		}
		JpaAuthSession check = (JpaAuthSession) obj;
		// Identity based equality
		return Objects.equals(getSessionId(), check.getSessionId());
	}

	@Override
	public int hashCode() {
		// Identity based equality
		return null == getSessionId() ? -1 : getSessionId().hashCode();
	}
}
