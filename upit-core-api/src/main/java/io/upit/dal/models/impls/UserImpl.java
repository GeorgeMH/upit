package io.upit.dal.models.impls;

import io.upit.dal.models.User;

import java.util.Objects;

import org.joda.time.DateTime;

public class UserImpl implements io.upit.dal.models.User {

	private String id;
	private String userName;
	private String email;
	private String password;
	private DateTime dateCreated;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;

	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public DateTime getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public int hashCode() {
		return null == getId() ? -1 : getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		//  Identity equals
		return Objects.equals(getId(), ((User) obj).getId());
	}
}
