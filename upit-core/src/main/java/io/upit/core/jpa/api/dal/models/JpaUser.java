package io.upit.core.jpa.api.dal.models;

import io.upit.core.api.models.User;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.primitives.Longs;

@Entity
public class JpaUser implements User {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Date dateCreated;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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
	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof JpaUser)) {
			return false;
		}
		JpaUser check = (JpaUser) obj;
		// Identity based equality
		return getId() == check.getId();
	}

	@Override
	public int hashCode() {
		// Identity based equality
		return Longs.hashCode(getId());
	}
	
	public static JpaUser toJpaUser(User user) {
		if (user instanceof JpaUser) {
			return (JpaUser) user;
		}
		JpaUser ret = new JpaUser();
		ret.setId(user.getId());
		ret.setEmail(user.getEmail());
		ret.setPassword(user.getPassword());
		ret.setDateCreated(user.getDateCreated());
		return ret;
	}
}
