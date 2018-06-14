package com.cgi.gitlab.issues.test;

import java.io.FileNotFoundException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cgi.gitlab.issues.map.Map2Java;
import com.cgi.gitlab.issues.model.Issue;

public class CSVTest {

	private final static String REST_URI = "https://gitlab.com/api/v4/projects/6993423/issues";
	private final static Client client = ClientBuilder.newClient();

	public static void main(String[] args) {
		List<Issue> issues = null;

		try {
			issues = Map2Java.csvToJava();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		importIssues(issues);
	}

	public static void importIssues(List<Issue> issues) {
		System.out.println(issues.size());
		for (Issue issue : issues) {
			WebTarget webTarget = null;
			webTarget = client.target(REST_URI);
			webTarget = addParams(issue, webTarget);
			Response response = webTarget.request(MediaType.APPLICATION_JSON)
					.header("PRIVATE-TOKEN", "i-EJigxFzypzFy-Jiri5").post(null);
			System.out.println(response.getStatus());
		}
	}

	private static WebTarget addParams(Issue issue, WebTarget webTarget) {
		return webTarget.queryParam("title", issue.getTitle()).queryParam("description", issue.getDescription())
				.queryParam("assignee", issue.getAssignee()).queryParam("milestone_id", issue.getMilestone())
				.queryParam("labels", issue.getLabels());
	}

}

/*
 * public static List<Issue> mapJavaBean() throws FileNotFoundException {
 * ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
 * strat.setType(Issue.class); String[] columns = new String[] { "title",
 * "description","assignee","milestone","labels" };
 * strat.setColumnMapping(columns); CsvToBean csv = new CsvToBean(); String
 * csvFilename = "D:\\Work\\gitlab\\Issues\\iss.csv"; CSVReader csvReader = new
 * CSVReader(new FileReader(csvFilename)); List<Issue> list = csv.parse(strat,
 * csvReader); for (Object object : list) { Issue issue = (Issue) object;
 * System.out.println(issue); } return list; }
 */
