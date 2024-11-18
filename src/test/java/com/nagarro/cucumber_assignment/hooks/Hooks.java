package com.nagarro.cucumber_assignment.hooks;

import org.openqa.selenium.WebDriver;
import com.nagarro.cucumber_assignment.pages.HomePage;
import com.nagarro.cucumber_assignment.pages.LoginPage;
import com.nagarro.cucumber_assignment.pages.SearchPage;
import com.nagarro.cucumber_assignment.stepDefinitions.HomeSteps;
import com.nagarro.cucumber_assignment.stepDefinitions.LoginSteps;
import com.nagarro.cucumber_assignment.stepDefinitions.SearchSteps;
import com.nagarro.cucumber_assignment.utilities.ConfigReader;
import com.nagarro.cucumber_assignment.utilities.WebDriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	private WebDriver driver;
	private WebDriverFactory webDriverFactory;
	private ConfigReader configReader;
	private HomePage homePage;
	private LoginPage loginPage;
	private SearchPage searchPage;
	private HomeSteps homeSteps;
	private LoginSteps loginSteps;
	private SearchSteps searchSteps;

	@Before
	public void beforeScenario() {
		webDriverFactory = new WebDriverFactory();
		driver = webDriverFactory.setUp();
		configReader = new ConfigReader();

		// Initialize Page Object Model Classes
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		searchPage = new SearchPage(driver);

		// Initialize HomeSteps and inject the current Hooks instance
		homeSteps = new HomeSteps(this);
		loginSteps = new LoginSteps(this);
		searchSteps = new SearchSteps(this);
	}

	@After
	public void afterScenario() {
		// Clean up WebDriver after test execution
		if (driver != null) {
			webDriverFactory.tearDown(driver);
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public HomePage getHomePage() {
		return homePage;
	}

	public LoginPage getLoginPage() {
		return loginPage;
	}

	public SearchPage getSearchPage() {
		return searchPage;
	}

	public ConfigReader getConfigReader() {
		return configReader;
	}

	public HomeSteps getHomeSteps() {
		return homeSteps;
	}

	public LoginSteps getLoginSteps() {
		return loginSteps;
	}

	public SearchSteps getSearchSteps() {
		return searchSteps;
	}

}
