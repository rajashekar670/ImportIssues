package com.cgi.gitlab.issues.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cgi.gitlab.issues.map.IssueProp;
import com.cgi.gitlab.issues.model.Issue;
import com.cgi.gitlab.issues.model.Milestone;

public class GitlabClient {

	private final static Client client = ClientBuilder.newClient();
	private static Set<String> milestones = null;
	private static Map<String, String> milestonesMap = null;

	public static void createIssues(List<Issue> issues) {
		createMilestones(issues);
		getMilestones();
		IssueProp prop = new IssueProp();
		final String ISSUES_URI = prop.get("gitlab.home.uri") + prop.get("project.id") + prop.get("gitlab.issues.uri");
		System.out.println(ISSUES_URI);
		boolean flag = false;
		System.out.println("Total issues : " + issues.size());
		int completed = 0;
		int fails = 0;
		for (Issue issue : issues) {
			String token = prop.get(issue.getReporter()) != null ? prop.get(issue.getReporter())
					: prop.get("gitlab.header.privatetoken.default");
			WebTarget webTarget = null;
			webTarget = client.target(ISSUES_URI);
			webTarget = addQueryParams(issue, webTarget);
			Response response = webTarget.request(MediaType.APPLICATION_JSON).header("PRIVATE-TOKEN", token).post(null);
			int status = response.getStatus();

			if (status != 201) {
				System.out.println(status + " : " + issue.getTitle() + webTarget.getUri());
				fails++;
				flag = true;
			} else {
				System.out.println(++completed);
				// System.out.println("Created Issue :" + issue.getTitle());
			}
		}
		if (flag == true)
			System.out.println("Total no of issues failed to create : " + fails);
		else
			System.out.println("Completed Successfully");
	}

	private static WebTarget addQueryParams(Issue issue, WebTarget webTarget) {

		String milestoneid = milestonesMap.get(issue.getMilestone());

		return webTarget.queryParam("title", issue.getTitle()).queryParam("description", issue.getDescription())
				.queryParam("assignee_ids", issue.getAssignee()).queryParam("labels", issue.getLabels())
				.queryParam("created_at", issue.getCreatedAt()).queryParam("due_date", issue.getDueDate())
				.queryParam("milestone_id", milestoneid == null ? "" : milestoneid);
	}

	public static void createMilestones(List<Issue> issues) {
		boolean flag = false;
		milestones = new HashSet<String>();
		IssueProp prop = new IssueProp();
		getMilestones();
		final String MILESTONES_URI = prop.get("gitlab.home.uri") + prop.get("project.id")
				+ prop.get("gitlab.milestones.uri");
		for (Issue issue : issues) {
			if (!milestonesMap.containsKey(issue.getMilestone())) {
				milestones.add(issue.getMilestone());
			}
		}
		System.out.println("Total milestones to create :" + milestones.size() + " : " + milestones);
		for (String milestone : milestones) {
			System.out.println(milestone);
			WebTarget webTarget = null;
			webTarget = client.target(MILESTONES_URI).queryParam("title", milestone);
			System.out.println(webTarget.getUri());
			Response response = webTarget.request(MediaType.APPLICATION_JSON)
					.header("PRIVATE-TOKEN", prop.get("gitlab.header.privatetoken.default")).post(null);
			int status = response.getStatus();

			if (status != 201) {
				System.out.println(status + " : " + milestone);
				flag = true;
			} else {
				System.out.println("Milestone created :" + milestone);
			}
		}
		if (flag == true)
			System.out.println("Some milestones are not created");
		else
			System.out.println("All milestones created");

	}

	public static void getMilestones() {

		IssueProp prop = new IssueProp();
		WebTarget webtarget = client
				.target(prop.get("gitlab.home.uri") + prop.get("project.id") + prop.get("gitlab.milestones.uri"));
		Milestone[] milestones = webtarget.queryParam("per_page", "100").request(MediaType.APPLICATION_JSON)
				.header("PRIVATE-TOKEN", prop.get("gitlab.header.privatetoken.default")).get(Milestone[].class);
		System.out.println(milestones.length);
		milestonesMap = new HashMap<String, String>();
		for (Milestone milestone : milestones) {
			milestonesMap.put(milestone.getTitle(), milestone.getId());
		}
		System.out.println(milestonesMap);
	}

}
