package io.upit.dal.models;

public interface LoginRequest {
	
	public String getUserName();
	public void setUserName(String userName);

	public String getPassword();
	public void setPassword(String password);

}
