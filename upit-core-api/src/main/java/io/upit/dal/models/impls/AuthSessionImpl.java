package io.upit.dal.models.impls;

import io.upit.dal.models.AuthSession;

import org.joda.time.DateTime;

public class AuthSessionImpl implements AuthSession {
	
	private String sessionId;
	
	private String userId;
	private String token;

	@Override
	public String getSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSessionId(String sessionId) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setToken(String authToken) {
		// TODO Auto-generated method stub

	}

	@Override
	public DateTime getCreated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreated(DateTime created) {
		// TODO Auto-generated method stub

	}

	@Override
	public DateTime getExpires() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExpires(DateTime expires) {
		// TODO Auto-generated method stub

	}

	@Override
	public DateTime getLastAccessed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DateTime setLastAccessed(DateTime lastAccessed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setActive(boolean active) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSecure(boolean secure) {
		// TODO Auto-generated method stub

	}

}
