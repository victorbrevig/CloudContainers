#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: elapse time for journey

  @tag1
  Scenario: Elapse time of journey for ten hours
    Given a logistic company with a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And a registered client with email "s184469@student.dtu.dk"
    And three containers registered to the client
 		And the containers are put on the journey containing "bananas", "milk" and "oranges" respectively
    When journey is started and run for 10 hours
    Then journey elapsed time is updated to 10 hours
    And data has been collected up till 10 hours
    And a succes response is given for journey 1 elapsed time 10

   @tag2
  Scenario: journey ends
    Given a logistic company with a journey from "Copenhagen" to "Oslo" with 5 hours to destination
    And a registered client with email "s184469@student.dtu.dk"
    And three containers registered to the client
 		And the containers are put on the journey containing "bananas", "milk" and "oranges" respectively
    When journey is started and run for 10 hours
    Then journey elapsed time is updated to 5 hours
    And data has been collected up till 5 hours
    And a response is returned that the journey has ended for journey 1 after 5 hours
