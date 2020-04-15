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
Feature: Add container to journey

  @tag1
  Scenario: Successfully add a container to journey
    Given a logistic company with a registered client
    And a container owned by the client
    And a valid journey
    When logistic company tries to put container on journey
    Then success message is displayed
	
	@tag2
  Scenario: Client does not exist
    Given a logistic company with a non-registered client
    And an available container
    And a valid journey
    When logistic company tries to put container on journey
    Then error message displayed saying that client does not exist
    
  @tag3
  Scenario: Container does not exist
    Given a logistic company with a registered client
    And an available container not owned by client
    And a valid journey
    When logistic company tries to put container on journey
    Then error message displayed saying that container does not exist
    
  @tag4
  Scenario: Journey does not exist
    Given a logistic company with a registered client
    And a container owned by the client
    And a non-registered journey
    When logistic company tries to put container on journey
    Then error message displayed saying that journey does not exist
	
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step

    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
