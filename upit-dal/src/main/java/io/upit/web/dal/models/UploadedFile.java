package io.upit.web.dal.models;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Indexed;
import com.github.jmkgreen.morphia.utils.IndexDirection;

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
