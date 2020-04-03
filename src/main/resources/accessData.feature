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
    Given a logistic company with a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And a registered client with email "s184469@student.dtu.dk"
    And three containers registered to the client
 		And the containers are put on the journey containing "bananas", "milk" and "oranges" respectively
 		And journey is started and run for 10 hours
 		When client 1 request the data for his container
    Then the status of the container is returned
    And a succes for data access is displayed

  @tag2
  Scenario: container does not exist
    Given a logistic company with a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And a registered client with email "s184469@student.dtu.dk"
 		When client 1 request the data for a container with ID 1010
    Then a error message is returned for data acces
  @tag3
  Scenario: container is not owned by client 
    Given a logistic company with a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And a registered client with email "s184469@student.dtu.dk"
 		When client 1 request the data for a container with ID 95
    Then a error message is returned for data acces
   @tag4
    Scenario: journey has not started
    Given a logistic company with a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And a registered client with email "s184469@student.dtu.dk"
    And three containers registered to the client
 		And the containers are put on the journey containing "bananas", "milk" and "oranges" respectively
 		When client 1 request the data for his container
    Then a error message is returned for journey has not started
#
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
