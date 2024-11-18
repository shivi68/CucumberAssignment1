@SearchPage
Feature: Search Page Functionality
   As a user, I want to be able to search an item and view the search results page.

	@SearchItem
  Scenario Outline: Search an Item through Search Box and Navigate to the Search Results Page
    Given the User is on the Home Page
    When the user enters "<searchItemName>" in the search box
    And the user clicks the search button
    Then the search results page should be displayed

    Examples:
      | searchItemName |
      | laptop         |
      #| tablet         |
      #| phone          |

  @AddToCart
  Scenario Outline: Search an item and add it to the cart
    Given the user is on the Home Page
    When the user enters "<searchItemName>" in the search box
    And the user clicks the search button
    And the user clicks on the Add to Cart button for the item 
    Then the pop up message appear Item Added 
    And the user click on Cart Link and should be able to navigate to the Cart Page
    And the added item "<itemName>" should be displayed in the cart

    Examples:
      | searchItemName | cartItemName |
      | laptop         | laptop   |
      #| tablet         | tablet   |
      #| phone          | phone    |