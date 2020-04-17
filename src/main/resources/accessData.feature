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
Feature: access data as client

  @tag1
  Scenario: access existing data
  	Given A logistic company
    And a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey containing "bananas", "milk" and "oranges" respectively
 		And journey is started and run for 10 hours
 		When client request the data for his container
    Then the status of the container is returned
    And a succes for data access is displayed


  @tag2
  Scenario: container is not owned by client 
    Given A logistic company
    And a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And an unowned container
 		When client request the data for a container he does not own
    Then a error message is returned for data access
   @tag3
    Scenario: journey has not started
    Given A logistic company
    And a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey containing "bananas", "milk" and "oranges" respectively
 		When client request the data for his container
    Then a error message is returned for journey has not started
#
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
