package com.nagarro.cucumber_assignment.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nagarro.cucumber_assignment.hooks.Hooks;
import com.nagarro.cucumber_assignment.pages.SearchPage;
import com.nagarro.cucumber_assignment.utilities.ConfigReader;
import com.nagarro.cucumber_assignment.utilities.WebDriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {

	private WebDriver driver;
	private SearchPage searchPage;
	private ConfigReader configReader;

	protected static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

	// Constructor (Hooks will provide the WebDriver)
	public SearchSteps(Hooks hooks) {
		this.configReader = hooks.getConfigReader();
		this.driver = hooks.getDriver();
		this.searchPage = hooks.getSearchPage();
	}

	// To navigate to the Home Page
	@Given("the User is on the Home Page")
	public void i_am_on_the_home_page() {
		logger.info("Navigating to the home page.");
		String url = configReader.getUrl();
		driver.get(url);
	}

	@When("the user enters {string} in the search box")
	public void whenUserEntersItemInSearchBox(String searchItemName) {
		logger.info("Searching for the item: " + searchItemName);
		searchPage.getSearchItemPage(searchItemName);
	}

	@And("the user clicks the search button")
	public void whenUserClicksSearchButton() {
		logger.info("Clicking the search button.");
		searchPage.getSearchButton();
	}

	@Then("the search results page should be displayed")
	public void thenSearchResultsPageShouldBeDisplayed() {
		logger.info("Verifying if search results page is displayed.");
		Assert.assertTrue(searchPage.isSearchPageDisplayed(), "Search results page is not displayed.");
	}

	// Step to click "Add to Cart" button
	@And("the user clicks on the Add to Cart button for the item")
	public void whenUserClicksAddToCartButtonForItem() {
		logger.info("Clicking the Add to Cart button for the item.");
		searchPage.getSearchAndAddToCart();
	}

	// Step to verify that the item is added to the cart
	@Then("the pop up message appear Item Added")
	public void thenPopUpItemAdded() {
		logger.info("Verifying pop-up message for item added.");
		Assert.assertTrue(searchPage.isItemAddedToCart(), "Item was not added to the cart.");
	}

	// Step to verify navigation to the cart page
	@And("the user click on Cart Link and should be able to navigate to the Cart Page")
	public void thenUserShouldBeNavigatedToCartPage() {
		logger.info("Verifying if user is navigated to the cart page.");
		Assert.assertTrue(searchPage.isCartPageDisplayed(), "User was not navigated to the cart page.");
	}

	// Step to verify that the added item is displayed in the cart
	@And("the added item {string} should be displayed in the cart")
	public void thenAddedItemShouldBeDisplayedInCart(String cartItemName) {
		logger.info("Verifying if the item " + cartItemName + " is displayed in the cart.");
		Assert.assertTrue(searchPage.isItemInCart(cartItemName), "The added item is not displayed in the cart.");
	}
}
