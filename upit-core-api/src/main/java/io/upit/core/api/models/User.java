package io.upit.core.api.models;

import java.util.Date;

public interface User {
	public long getId();
	public void setId(long id);
	
	public String getUserName();
	void setUserName(String userName);

	public String getEmail();
	public void setEmail(String email);

	public String getPassword();
	public void setPassword(String password);

	public Date getDateCreated();
	public void setDateCreated(Date dateCreated);
}
