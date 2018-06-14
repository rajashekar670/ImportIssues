package com.cgi.gitlab.issues.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cgi.gitlab.issues.map.IssueProp;
import com.cgi.gitlab.issues.model.Issue;

public class MileStonesClient {

	private static Set<String> milestones = null;
	private final static Client client = ClientBuilder.newClient();

	public static void create(List<Issue> issues) {
		boolean flag = false;
		milestones = new HashSet<String>();
		IssueProp prop = new IssueProp();
		final String MILESTONES_URI = prop.get("gitlab.home.uri") + prop.get("project.id")
				+ prop.get("gitlab.milestones.uri");
		for (Issue issue : issues) {
			milestones.add(issue.getMilestone());
		}
		System.out.println("Total milestones to create :" + milestones.size() + " : " + milestones);
		for (String milestone : milestones) {
			WebTarget webTarget = null;
			webTarget = client.target(MILESTONES_URI).queryParam("title", milestone);
			System.out.println(webTarget.getUri());
			Response response = webTarget.request(MediaType.APPLICATION_JSON)
					.header("PRIVATE-TOKEN", prop.get("gitlab.header.privatetoken.default")).post(null);
			int status = response.getStatus();

			if (status != 201) {
				System.out.println(status + " :" + milestone);
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
}
