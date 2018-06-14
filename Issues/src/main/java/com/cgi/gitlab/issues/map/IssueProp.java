package com.cgi.gitlab.issues.map;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class IssueProp {
	private static Properties props;

	public IssueProp() {
		props = new Properties();
		try {
			props.load(new FileInputStream(new File(System.getProperty("user.dir") + "/application.properties")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String get(String key) {
		if (key != null)
			return props.getProperty(key);
		else
			return null;
	}

	public boolean contains(String key) {
		return props.containsKey(key);
	}
}
