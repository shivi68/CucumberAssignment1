@LoginPage
Feature: Amazon Login Page Functionality
  As a user, I want to be able to log in to my Amazon account using valid credentials
  and see appropriate error messages for invalid or missing credentials.

  @LoginSuccessful
  Scenario: Successful Login with valid credentials
    Given the user is on the Home Page
    When the user hovers over the "Account & Lists" menu
    Then the "Sign In" button should be visible and user clicks on the button
    Then the user is on the login page
    When the user enters an email "shivangiguptadln@gmail.com"
    And the user clicks the Continue button
    And the user enters a password "Shivangi168"
    And the user clicks the Amazon Sign-In button
    Then the user should be logged in successfully
    
    @BlankEmail
    Scenario: Blank Email and Get Error Message
     Given the user is on the Home Page
     When the user hovers over the "Account & Lists" menu
     Then the "Sign In" button should be visible and user clicks on the button
     Then the user is on the login page
     When the user enters an email ""
     And the user clicks the Continue button
     Then an error message email required should be displayed
     
    @BlankPassword
    Scenario: Blank Passwor and Get Error Message
     Given the user is on the Home Page
     When the user hovers over the "Account & Lists" menu
     Then the "Sign In" button should be visible and user clicks on the button
     Then the user is on the login page
     When the user enters an email "shivangiguptadln@gmail.com"
     And the user clicks the Continue button
     And the user enters a password ""
     And the user clicks the Amazon Sign-In button
     Then an error message password required should be displayed
    