package com.cgi.gitlab.issues.model;

public class Issue {

	private String title;
	private String description;
	private String assignee;
	private String milestone;
	private String labels;
	private String createdAt;
	private String dueDate;
	private String reporter;
	private String timeEstimate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getTimeEstimate() {
		return timeEstimate;
	}

	public void setTimeEstimate(String timeEstimate) {
		this.timeEstimate = timeEstimate;
	}

	@Override
	public String toString() {
		return "Issue [title=" + title + ", description=" + description + ", assignee=" + assignee + ", milestone="
				+ milestone + ", labels=" + labels + ", createdAt=" + createdAt + ", dueDate=" + dueDate + ", reporter="
				+ reporter + ", timeEstimate=" + timeEstimate + "]";
	}

	public Issue(String title, String description, String assignee, String milestone, String labels, String createdAt,
			String dueDate, String reporter, String timeEstimate) {
		super();
		this.title = title;
		this.description = description;
		this.assignee = assignee;
		this.milestone = milestone;
		this.labels = labels;
		this.createdAt = createdAt;
		this.dueDate = dueDate;
		this.reporter = reporter;
		this.timeEstimate = timeEstimate;
	}

	public Issue() {
		super();
	}

}
