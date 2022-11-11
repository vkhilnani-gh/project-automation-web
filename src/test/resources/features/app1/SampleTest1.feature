@sampleTest
Feature: Sample Test 1


#  Scenario: Access google website
#    Given I navigate to google website
#    When I click on Gmail
#    #When I type in an item in search bar

  Scenario: Selenium dev website test with Page containing both elements and methods
    Given I navigate to selenium dev
    Then I click on submit button

  Scenario: Selenium dev website test with Page containing elements and methods in Action class
    Given I navigate to selenium dev
    Then I click on submit button via action class

