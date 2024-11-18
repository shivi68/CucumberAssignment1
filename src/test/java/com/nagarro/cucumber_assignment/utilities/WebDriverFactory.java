package com.nagarro.cucumber_assignment.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	protected WebDriver driver;
	protected ConfigReader configReader;

	protected static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

	public WebDriver setUp() {
		configReader = new ConfigReader();

		String browser = configReader.getProperty("browser");
		if (browser == null) {
			logger.error("Browser configuration is missing in the properties file.");
			throw new IllegalArgumentException("Browser not specified in the config file.");
		}

		boolean isHeadless = Boolean.parseBoolean(configReader.getProperty("headless"));

		logger.info("Setting up WebDriver for browser: " + browser);

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			if (isHeadless) {
				chromeOptions.addArguments("--headless");
			}
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fireFoxOptions = new FirefoxOptions();
			if (isHeadless) {
				fireFoxOptions.addArguments("--headless");
			}
			driver = new FirefoxDriver(fireFoxOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			if (isHeadless) {
				edgeOptions.addArguments("--headless");
			}
			driver = new EdgeDriver(edgeOptions);
			break;

		default:
			logger.error("Unsupported browser specified: " + browser);
			throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");

		}

		driver.manage().window().maximize();
		String url = configReader.getProperty("url");
		if (url != null) {
			logger.info("Navigating to URL: " + url);
			driver.get(url);
		} else {
			throw new RuntimeException("URL is not specified in the Config file.");
		}

		return driver;
	}

	public void tearDown(WebDriver driver) {
		if (driver != null) {
			driver.quit();
			logger.info("WebDriver closed.");
		}
	}
}
