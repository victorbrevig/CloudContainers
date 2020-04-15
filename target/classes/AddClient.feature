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
Feature: Adding a client to register

  @tag1
  Scenario: New client succesfully added
    Given A none existing client 
    When Informations is entered
    Then display success message
 
 
  @tag2
  Scenario: Client already exists
    Given existing client
    When repeated information is entered
    Then error message is thrown
 
  @tag3
   Scenario: Missing parameter
    Given A none existing client
    When information with missing parameters
    Then missing parameter error message is thrown
 
  @tag4
   Scenario: Email is invalid
    Given A logistic company
    When invalid email "wrongEmail" is entered
    Then invalid email error is displayed for "wrongEmail"
   
  @tag5
   Scenario: Birthdate is invalid
    Given A logistic company
    When invalid birthdate "111-02-1992" is entered
    Then invalid birtdate error is displayed for "111-02-1992"
