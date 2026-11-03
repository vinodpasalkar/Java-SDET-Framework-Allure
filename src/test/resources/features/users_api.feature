@api
Feature: Users REST API
  As an automation engineer
  I want to verify the Users API endpoints
  So that I can validate status codes, response headers, and JSON payloads

  Background:
    Given the user API service is available

  @smoke
  Scenario: Get user details by ID
    When I send a GET request to "/users/42"
    Then the response status code should be 200
    And the response header "Content-Type" should contain "application/json"
    And the response JSON should contain "id" equal to 42
    And the response JSON should contain "name" equal to "Nagesh"
    And the response JSON should contain "role" equal to "SDET"

  @regression
  Scenario: Create a new user with valid details
    When I send a POST request to "/users" with payload:
      """
      {
        "name": "Vinod",
        "role": "SDET"
      }
      """
    Then the response status code should be 201
    And the response header "Content-Type" should contain "application/json"
    And the response JSON should contain "id" equal to 101
    And the response JSON should contain "name" equal to "Vinod"
    And the response JSON should contain "role" equal to "SDET"

  @negative
  Scenario: Query for non-existent user returns 404
    When I send a GET request to "/users/999"
    Then the response status code should be 404
    And the response header "Content-Type" should contain "application/json"
    And the response JSON should contain "error" equal to "User not found"
