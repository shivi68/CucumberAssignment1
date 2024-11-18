@HomePage
Feature: Home Page Functionality

  @HomePageTitle
  Scenario: Verify Home Page Title
    Given I am on the Home Page
    When I get the title of the page
    Then The page title should be "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in"

  @HamburgerMenu
  Scenario: Verify Hamburger Menu Visibility
    Given I am on the Home Page
    When I open the hamburger menu by clicking All
    Then The hamburger menu should be visible
    
  @CustomerService
  Scenario: Verify Navigation to Customer Service Page 
 		Given I am on the Home Page
 		When I click on customer service link
 		Then I should be navigated to the Customer Service Page
 
  