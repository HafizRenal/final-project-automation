Feature: DummyAPI User Management

  Background:
    Given the API base URL is "https://dummyapi.io/data/v1"

  @api
  Scenario: Get list of users
    When I send GET request to "/user?limit=5"
    Then the response status code should be 200
    And the response should contain field "data"

  @api
  Scenario: Get user by valid ID
    When I send GET request to "/user/60d0fe4f5311236168a109f3"
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
    When I send POST request to "/user/create" with body:
      """
      {
        "firstName": "Hafiz",
        "lastName": "Renal",
        "email": "hafizrenal.final2026@example.com"
      }
      """
    Then the response status code should be 200
    And the response should contain field "id"
    And the response should contain field "firstName"

  @api
  Scenario: Get user with invalid ID returns error
    When I send GET request to "/user/invalid_id_123"
    Then the response status code should be 400
