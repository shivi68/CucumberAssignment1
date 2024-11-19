package com.nagarro.cucumber_assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        // Calls BasePage constructor which initializes PageFactory
        super(driver);
    }

    @FindBy(id = "nav-hamburger-menu")
    private WebElement allMenu;

    @FindBy(xpath = "//a[normalize-space(text()) = 'Customer Service']")
    private WebElement customerServiceLink;

    @FindBy(xpath = "//h1[contains(text(), 'Hello. What can we help you with?')]")
    private WebElement customerServiceHeader;

    // Log4j2 Logger
    protected static final Logger logger = LogManager.getLogger(HomePage.class);

    // Method to get Page Title
    public String getHomePageTitle() {
        logger.info("Getting home page title.");
        return driver.getTitle();
    }

    // Method to click on the hamburger menu
    public void openHamburgerMenu() {
        logger.info("Opening hamburger menu.");
        clickElement(allMenu);
    }

    public boolean isMenuVisible() {
        logger.info("Checking if hamburger menu is visible.");
        waitForVisibility(allMenu);
        boolean isVisible = allMenu.isDisplayed();
        logger.debug("Hamburger menu visibility: {}", isVisible);
        return isVisible;
    }

    // Method to click on the Customer Service Link
    public void navigateToCustomerService() {
        logger.info("Navigating to Customer Service page.");
        closePopUpIfVisible();  // Close any pop-up before clicking the customer service link
        clickElement(customerServiceLink);
        if (isCustomerServicePageDisplayed()) {
            logger.info("Customer Service page is displayed successfully.");
        } else {
            logger.warn("Customer Service page is not displayed.");
        }
    }

    // Method to check if the Customer Service page has loaded
    public boolean isCustomerServicePageDisplayed() {
        logger.info("Checking if Customer Service page is displayed.");
        waitForVisibility(customerServiceHeader);
        boolean isDisplayed = customerServiceHeader.isDisplayed();
        logger.debug("Customer Service page visibility: {}", isDisplayed);
        return isDisplayed;
    }

    public void closePopUpIfVisible() {
    try {
        // Correct way to initialize WebDriverWait with Duration
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use Duration here
        WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".close, .dismiss, .modal-close")));

        // If the close button is found and visible, click it to close the pop-up
        if (closeButton.isDisplayed()) {
            closeButton.click();
            logger.info("Closed pop-up.");
        }
    } catch (NoSuchElementException | TimeoutException e) {
        // If the close button or pop-up is not found, it's fine, just log it
        logger.info("No pop-up found or pop-up is not visible.");
    }
}
}
