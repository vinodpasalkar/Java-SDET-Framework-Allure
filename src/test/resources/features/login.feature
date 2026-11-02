Feature: Login
  # Verifies a successful sign-in journey.
  Scenario: Successful login
    Given the login page is open
    When I sign in with username "tomsmith" and password "SuperSecretPassword!"
    Then I should see a successful login message
