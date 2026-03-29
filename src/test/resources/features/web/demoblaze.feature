Feature: Demoblaze Web UI Tests

  Background:
    Given I open the Demoblaze homepage

  @web
  Scenario: Successful login with valid credentials
    When I click on Sign in button
    And I enter login username "hafizrenal" and password "Test1234"
    And I submit the login form
    Then I should see the username "hafizrenal" in the navbar

  @web
  Scenario: Failed login with invalid credentials
    When I click on Sign in button
    And I enter login username "wronguser123" and password "wrongpass123"
    And I submit the login form
    Then I should see an alert message

  @web
  Scenario: Browse product categories
    When I click on "Phones" category
    Then I should see phone products listed

  @web
  Scenario: View product detail
    When I click on "Phones" category
    And I click on the first product
    Then I should see the product detail page
    And I should see an "Add to cart" button

  @web
  Scenario: Add product to cart
    Given I am logged in with username "hafizrenal" and password "Test1234"
    When I click on "Phones" category
    And I click on the first product
    And I click Add to cart button
    Then I should see cart confirmation