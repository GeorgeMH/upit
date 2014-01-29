package io.upit.core.dal.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.primitives.Longs;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Date dateCreated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		User check = (User) obj;
		// Identity based equality
		return getId() == check.getId();
	}

	@Override
	public int hashCode() {
		// Identity based equality
		return Longs.hashCode(getId());
	}
}
