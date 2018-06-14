package com.cgi.gitlab.issues.model;

public class NewIssueResponse {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "NewIssueResponse [id=" + id + "]";
	}

}
