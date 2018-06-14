package com.cgi.gitlab.issues.model;

public class Milestone {

	private String id;
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Milestones [id=" + id + ", title=" + title + "]";
	}

	public Milestone() {
		super();
	}

	public Milestone(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

}
