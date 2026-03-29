Feature: Demoblaze Web UI Tests

  Background:
    Given I open the Demoblaze homepage

  @web
  Scenario: Successful login with valid credentials
    When I click on Sign in button
    And I enter login username "admin" and password "password"
    And I submit the login form
    Then I should see the username "admin" in the navbar

  @web
  Scenario: Failed login with invalid credentials
    When I click on Sign in button
    And I enter login username "wronguser" and password "wrongpass"
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
    Given I am logged in with username "admin" and password "password"
    When I click on "Phones" category
    And I click on the first product
    And I click Add to cart button
    Then I should see cart confirmation