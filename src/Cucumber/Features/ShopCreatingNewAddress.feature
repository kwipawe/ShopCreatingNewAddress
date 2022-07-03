@shop
Feature: Creating new address after login

  Scenario: Create new alias, address, city, postal code, country and phone number

    Given I am on shop main page
    When I go to sign in page
    And I log in as an user using "sihrgcfkwpbwqlolhb@nthrw.com" and "secretpass"
    And I go to Add First Address Page
    And I put  "Alias", "Baker Str", "Kent", "00001", "111111111" and save
    Then I see "Alias", "Baker Str", "Kent", "00001", "111111111"
    And I delete created address
    And I check if created address is deleted