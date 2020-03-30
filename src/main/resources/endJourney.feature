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
Feature: End a journey

  @tag1
  Scenario: a journey successfully ended
    Given a logistic company with a registered journey
    And one container allocated to a client is added to the journey
    When logistic company tries to end journey
    Then message displayed saying journey successfully ended for 1 containers
    
  @tag2
  Scenario: journey does not exist
    Given a logistic company with a non-registered journey
    And a container allocated to a client is added to the journey pt2
    When logistic company tries to end journey
    Then error message displayed saying journey does not exist

  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step

    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
