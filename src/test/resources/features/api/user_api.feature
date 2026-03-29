Feature: DummyAPI User Management

  Background:
    Given the API base URL is "https://dummyapi.io/data/v1"

  @api
  Scenario: Get list of users returns valid data
    When I send GET request to "/user?limit=5"
    Then the response status code should be 200
    And the response should contain field "data"
    And the response data list should not be empty
    And the response time should be less than 5000 milliseconds

  @api
  Scenario: Get user by valid ID returns correct data
    When I send GET request to "/user/60d0fe4f5311236168a109f3"
    Then the response status code should be 200
    And the response should contain field "firstName"
    And the response should contain field "lastName"
    And the response should contain field "email"
    And the response field "firstName" should have value "Hawkish"
    And the response time should be less than 5000 milliseconds

  @api
  Scenario: Get list of tags returns non-empty list
    When I send GET request to "/tag"
    Then the response status code should be 200
    And the response should contain a list of tags
    And the response time should be less than 5000 milliseconds

  @api
  Scenario: Create user with duplicate email returns error
    When I send POST request to "/user/create" with body:
      """
      {
        "firstName": "Hafiz",
        "lastName": "Renal",
        "email": "hafizrenal.final2026@example.com"
      }
      """
    Then the response status code should be 400

  @api
  Scenario: Get user with invalid ID returns error
    When I send GET request to "/user/invalid_id_123"
    Then the response status code should be 400