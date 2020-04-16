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
Feature: Client wish to filter journey

  @tag1
  Scenario: client wish to filter journeys after port of origin
  	Given A logistic company
    Given three journeys with port of origin "Texas", "Cancun" and "Texas"
    When journeys are filtered for "Texas"
    Then display filtered set of journeys with port of origin "Texas"
    
  @tag2
  Scenario: client wish to filter journeys after destination
  	Given A logistic company
    Given three journeys with destination "Texas", "Cancun" and "Texas"
    When journeys are filtered for destination "Texas"
    Then display filtered set of journeys with destination "Texas"
    
  @tag3
  Scenario: no journey matching port of destination given by input
  	Given A logistic company
    Given three journeys with port of origin "Texas", "Cancun" and "Texas"
    When journeys are filtered for "Roskilde"
    Then get empty set
    
  @tag4
  Scenario: no journey matching destination given by input
  	Given A logistic company
    Given three journeys with destination "Texas", "Cancun" and "Texas"
    When journeys are filtered for destination "Roskilde"
    Then get empty set
    

  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step

    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
