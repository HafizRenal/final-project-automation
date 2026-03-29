Feature: DummyAPI User Management

  Background:
    Given the API base URL is "https://dummyapi.io/data/v1"

  @api
  Scenario: Get user by valid ID
    When I send GET request to "/user/60d0fe4f5311236168a109ca"
    Then the response status code should be 200
    And the response should contain field "firstName"
    And the response should contain field "lastName"
    And the response should contain field "email"

  @api
  Scenario: Get list of tags
    When I send GET request to "/tag"
    Then the response status code should be 200
    And the response should contain a list of tags

  @api
  Scenario: Create a new user
    When I send POST request to "/user" with body:
      """
      {
        "firstName": "Hafiz",
        "lastName": "Renal",
        "email": "hafizrenal.test@example.com"
      }
      """
    Then the response status code should be 200
    And the response should contain field "id"
    And the response should contain field "firstName"

  @api
  Scenario: Get user with invalid ID returns error
    When I send GET request to "/user/invalid_id_123"
    Then the response status code should be 400