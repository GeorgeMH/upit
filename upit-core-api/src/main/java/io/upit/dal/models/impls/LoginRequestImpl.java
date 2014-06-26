package io.upit.dal.models.impls;

import io.upit.dal.models.LoginRequest;

public class LoginRequestImpl implements LoginRequest {

	private String userName;
	private String password;

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}


}
