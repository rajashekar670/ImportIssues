package com.cgi.gitlab.issues.map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.cgi.gitlab.issues.model.Issue;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class Map2Java {

	@SuppressWarnings("deprecation")
	public static List<Issue> csvToJava() throws FileNotFoundException {
		IssueProp prop = new IssueProp();
		ColumnPositionMappingStrategy<Issue> strat = new ColumnPositionMappingStrategy<Issue>();
		strat.setType(Issue.class);
		String[] columns = prop.get("file.issues.header").split(",");
				//new String[] { "title", "description", "assignee", "milestone", "labels", "createdAt",
				//"dueDate","reporter"};
		strat.setColumnMapping(columns);
		CsvToBean<Issue> csv = new CsvToBean<Issue>();
		String csvFilename = System.getProperty("user.dir")+prop.get("file.name.issues");
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		return csv.parse(strat, csvReader);
	}
}
