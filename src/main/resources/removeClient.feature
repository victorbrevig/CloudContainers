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
Feature: Remove client

  @tag1
  Scenario: A logistic company wants to remove a client
  	Given A logistic company
    Given A client with the clientID 1
    When the logistic company removes a client with the clientID 1
    Then the client is deleted with clientID 1
  
  @tag2
  Scenario: A logistic company wants to remove a client
    Given A logistic company
    Given A client with the email "email123@mail.dk"
    When the logistic company removes a client with an email "j1@gmail.com"
    Then the client is deleted with email "j1@gmail.com"
    
 @tag3
  Scenario: A logistic company tries to remove a non existing client
    Given A logistic company
    Given A none existing client
		When the logistic company removes a client with an email "e"
    Then nothing happens
  @tag4
  Scenario: A logistic company tries to remove a non existing client
    Given A logistic company
    Given A none existing client
		When the logistic company removes a client with the clientID 20
    Then nothing happens
    