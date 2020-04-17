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
Feature: Free a container

  @tag1
  Scenario: a container is successfully freed
  	Given A logistic company
    And existing client
    And an unowned container
    And a container is allocated
    When logistic company frees one container
    Then A succes message is displayed for freeing a container

  @tag2
  Scenario: container is on journey
  	Given A logistic company
    And existing client
    And an unowned container
    And a container is allocated
    And a valid journey
    And logistic company tries to put container on journey
    When logistic company frees one container
    Then A error message is displayed that container is on journey

  @tag3
  Scenario: container is not owned
  	Given A logistic company
    And existing client
    And an unowned container
    When logistic company frees one container
    Then A error message is displayed as container is not owned



