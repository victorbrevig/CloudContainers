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
    And client with a name "Chris"
    And client with a mail "yeet@dtu.dk"
    And client with a phone number "12345678"
    And client with an age "21"
    And client with a gender "male"
    When Informations is entered
    Then display success message
 
 
  @tag2
  Scenario: Client already exists
    Given existing client
    And client with a name "Chris"
    And client with a mail "yeet@dtu.dk"
    And client with a phone number "12345678"
    And client with an age "21"
    And client with a gender "male"
    When Informations is entered
    Then error message is thrown
 
  #@tag2
  #Scenario: Client already exists
    #Given 
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes
    #
 #	@tag3
  #Scenario: Missing parameter
    #Given I want to write a step with precondition
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes
#
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
