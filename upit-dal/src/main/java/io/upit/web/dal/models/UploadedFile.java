package io.upit.web.dal.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.utils.IndexDirection;

@Entity
public class UploadedFile {
	
	@Id
	private ObjectId id;
	
	@Indexed(value=IndexDirection.ASC, unique=true)
	private String hash;
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
