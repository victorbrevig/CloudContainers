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
Feature: Logistic company search for client

  @tag1
  Scenario: Searching for an existing client
    Given A client with the email "email123@mail.dk"
    When The logistic company searches for "email123@mail.dk"
    Then The client is returned for "email123@mail.dk" and a succes message is displayed
    

  @tag2
  Scenario: Searching for an existing client
    Given A client with the clientID 1
    When The logistic company searches for 1
    Then The client is returned for 1 and a succes message is displayed

	@tag3
  Scenario: Searching for a non existing client
    Given A none existing client for email search
    When The logistic company searches "e"
    Then A failure message is returned for "e"
 
  @tag4
  Scenario: Searching for a non existing client
    Given A none existing client for ID search
    When The logistic company searches 10
    Then A failure message is returned for 10
