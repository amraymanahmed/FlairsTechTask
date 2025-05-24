Feature: OrangeHRM User Management

  Scenario: Add and delete user from Admin module
    Given user is on login page
    When user enters username and password and click on Login button
    Then user navigates to Admin tab
    And user notes the current number of records
    And user adds a new user
    Then the number of records should increase by 1
    When user searches for the new user
    And user deletes the new user
    Then the number of records should decrease by 1
