package io.upit.core.jpa.api.dal.models;

import io.upit.core.api.models.LoginRequest;

public class JpaLoginRequest implements LoginRequest {
	
	private String userName;
	
	private String passWord;

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getPassWord() {
		return passWord;
	}

	@Override
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
