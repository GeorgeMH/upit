package io.upit.dal.models.pojos;

import io.upit.dal.models.Paste;

import java.util.Objects;

import org.joda.time.DateTime;

public class PasteImpl implements Paste {

	private String id;

	private String text;

	private String userId;

	private DateTime dateCreated;

	private String parentId;

	private String syntaxId;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public DateTime getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(DateTime date) {
		this.dateCreated = date;
	}

	@Override
	public String getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getSyntaxId() {
		return syntaxId;
	}

	@Override
	public void setSyntaxId(String syntaxId) {
		this.syntaxId = syntaxId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Paste)) {
			return false;
		}
		return Objects.equals(getId(), ((Paste) obj).getId());
	}
}
