package com.cgi.gitlab.issues.test;

import java.io.FileNotFoundException;
import java.util.List;

import com.cgi.gitlab.issues.client.MileStonesClient;
import com.cgi.gitlab.issues.map.Map2Java;
import com.cgi.gitlab.issues.model.Issue;

public class ImportMilestones {

	public static void main(String[] args) {
		List<Issue> issues = null;
		try {
			issues = Map2Java.csvToJava();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MileStonesClient.create(issues);
	}
}
