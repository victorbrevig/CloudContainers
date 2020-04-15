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
Feature: A container has a history log of its journeys, clients and status data

  @tag1
  Scenario: logistic company wants to see history of container
  	Given A logistic company
    And a registered client with email "s184469@student.dtu.dk"
    And a container registered to the client
    And the container has been on one journey from "Copenhagen" to "Malmo" and another journey from "Oslo" to "Copenhagen"
		When container history is requested by logistic company
		Then an array containing pairs of journeyIDs and clientIDs are returned
		And response message saying that history of container was successfully retrieved
    
  @tag2
  Scenario: container does not exist
  	Given A logistic company
    And a registered client with email "s184469@student.dtu.dk"
    And an non-registered container
		When container history is requested by logistic company
	  Then response message saying that container does not exist
    
  @tag3
  Scenario: client wants to see history of container owned
  	Given A logistic company
    And a registered client with email "s184469@student.dtu.dk"
    And a container registered to the client
    And the container has been on one journey from "Copenhagen" to "Malmo" and another journey from "Oslo" to "Copenhagen"
		When container history is requested by client
		Then an array containing relevant journeys are returned
		And response message saying that history of container was successfully retrieved to client
    
  @tag4
  Scenario: client wants to see history of container but container does not exist
  	Given A logistic company
    And a registered client with email "s184469@student.dtu.dk"
		And an non-registered container
		When container history is requested by client
		Then response message saying that container does not exist to client

  @tag5
  Scenario: client wants to see history of container owned with no journeys
  	Given A logistic company
    And a registered client with email "s184469@student.dtu.dk"
    And a container registered to the client
		When container history is requested by client
		Then an array containing relevant journeys are returned with size 0
		And response message saying that history of container was successfully retrieved to client


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
