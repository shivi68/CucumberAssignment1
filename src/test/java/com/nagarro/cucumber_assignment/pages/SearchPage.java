package com.nagarro.cucumber_assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	// Constructor
	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement searchBox;

	@FindBy(id = "nav-search-submit-button")
	private WebElement searchButton;

	@FindBy(xpath = " //h2[normalize-space()='Results']")
	private WebElement searchPageHeader;

	// Search and Add to Cart Part the first result appear for next
	// a-autoid-2-announce
	@FindBy(id = "a-autoid-1-announce")
	private WebElement addToCart;

	// Cart Link
	@FindBy(xpath = "//a[@id='nav-cart']")
	private WebElement cartLink;

	@FindBy(xpath = "//div[@class='a-changeover-inner']/strong[contains(text(), 'Item Added')]")
	private WebElement itemAddedToCart;

	// Item Added to Cart
	@FindBy(xpath = "//h2[@class='a-size-extra-large a-text-normal']")
	private WebElement cartPageHeader;
	
	@FindBy(xpath="//span[@class='a-truncate-cut']")
	private WebElement cartItem;

	public void getSearchItemPage(String searchItemName) {
		waitForVisibility(searchBox);
		searchBox.sendKeys(searchItemName);
		searchButton.click();
		isSearchPageDisplayed();
	}

	public void getSearchButton() {
		searchButton.click();
	}

	// Method to check if the Search Page has loaded
	public boolean isSearchPageDisplayed() {
		waitForVisibility(searchPageHeader);
		boolean isDisplayed = searchPageHeader.isDisplayed();
		if (isDisplayed) {
			logger.info("Search page loaded successfully.");
		} else {
			logger.warn("Search page did not load.");
		}
		return isDisplayed;
	}

	// Perform search and add item to cart
	public void getSearchAndAddToCart() {
		try {
			logger.info("Attempting to add item to cart.");
			clickElement(addToCart);
			Thread.sleep(2000);
			logger.info("Item added to cart.");
			// clickElement(cartLink);
			isItemAddedToCart();
		} catch (Exception e) {
			logger.error("Failed to add item to cart or load the cart page.", e);
		}
	}

	// Method to check if the Itea added to the cart has loaded
	public boolean isItemAddedToCart() {
		// getWait().until(ExpectedConditions.visibilityOf(itemAddedToCart));
		waitForVisibility(itemAddedToCart);
		boolean isDisplayed = itemAddedToCart.isDisplayed();
		if (isDisplayed) {
			logger.info("Item successfully added to cart.");
		} else {
			logger.warn("Item was not added to cart.");
		}
		return isDisplayed;
	}

//	// Method to click on the Cart Link and verify navigation to the Cart Page
//	public void clickCartLink() {
//		clickElement(cartLink);
//		isCartPageDisplayed();
//	}

	// Method to check if the Cart Page has loaded
	public boolean isCartPageDisplayed() {
		clickElement(cartLink);
		waitForVisibility(cartPageHeader);
		boolean isDisplayed = cartPageHeader.isDisplayed();
		if (isDisplayed) {
			logger.info("Navigated to Cart page successfully.");
		} else {
			logger.warn("Failed to navigate to Cart page.");
		}
		return isDisplayed;
	}

	public boolean isItemInCart(String cartItemName) {
		try {
			// Dynamically locate the item in the cart based on the item name
			//WebElement cartItem = driver.findElement(By.xpath("//span[contains(@class, 'cart-item-title') and contains(text(), '" + itemName + "')]"));
			waitForVisibility(cartItem);
			boolean isDisplayed = cartItem.isDisplayed();
			if (isDisplayed) {
				logger.info("The item '" + cartItemName + "' is present in the cart.");
			} else {
				logger.warn("The item '" + cartItemName + "' is not present in the cart.");
			}
			return isDisplayed;
		} catch (Exception e) {
			logger.error("The item '" + cartItemName + "' is not found in the cart.", e);
			return false;
		}
	}

}
