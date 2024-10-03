Feature: User Registration

  Scenario Outline: Create a new user with dynamic data
    Given The user registration service is running
    When I create a new user with the following credentials:
      | key      | value      |
      | username | <username> |
      | password | <password> |
    Then the user should be added to the database with username "<username>"
    Examples:
      | username | password  |
      | username | passWord1 |
      | qweaaa   | Sqqass221 |
      | LLAAss00 | PPAacc991 |
      | 1235467  | 123456aA  |


  Scenario Outline: Create a duplicated user with dynamic data
    Given The user registration service is running
    When I create a duplicated user with the following credentials:
      | key      | value      |
      | username | <username> |
      | password | <password> |
    Then there should be only one user with username "<username>"
    Examples:
      | username | password  |
      | username | passWord1 |
      | qweaaa   | Sqqass221 |
      | LLAAss00 | PPAacc991 |
      | 1235467  | 123456aA  |