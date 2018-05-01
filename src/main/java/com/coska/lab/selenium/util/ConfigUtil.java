package com.coska.lab.selenium.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	private static Properties configurations = new Properties();
	private static String env = System.getProperty("env");
	private static String propsPath = "src/main/resources/$ENV/conf.properties";
	static {
		if(env == null) {
			env = "local";
		}
		propsPath = propsPath.replace("$ENV", env);

		try {
			FileInputStream fis = new FileInputStream(propsPath);
			BufferedInputStream bis = new BufferedInputStream(fis);
			configurations.load(bis);

		} catch(IOException ie) {
			Log.trace("...config file loading failed...");
		}
	}
	
	public static String getConfig(String key) {
		return configurations.getProperty(key);
	}
}
