package com.nagarro.cucumber_assignment.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nagarro.cucumber_assignment.utilities.ConfigReader;

public class ConfigReader {

	protected static final Logger logger = LogManager.getLogger(ConfigReader.class);
	private Properties properties;

	public ConfigReader() {
		properties = new Properties();
		try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
			properties.load(fileInputStream);
			logger.info("Configuration file loaded successfully.");
		} catch (IOException e) {
			logger.error("Failed to load properties file.", e);
			throw new RuntimeException("Failed to load properties file.", e);
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public int getGlobalWaitTime() {
		return Integer.parseInt(properties.getProperty("globalWaitTime"));
	}

	public String getUrl() {
		return properties.getProperty("url");
	}

}
