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
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario: Logistic company wants to update the port of origin of a journey
    Given A logistic company with a registered journey with PoO of "Texas"
    When the logistic company tries to update port of origin to "Cape Town"
    Then success message is displayed for port of origin "Cape Town"
    
  @tag2
  Scenario: Logistic company wants to update the destination of a journey
    Given A logistic company with a registered journey with destination of "Copenhagen"
    When the logistic company tries to update destination to "Tokyo"
    Then success message is displayed for destination "Tokyo"
    
  @tag3
  Scenario: Journey does not exist
    Given A logistic company with a non-registered journey with destination "Tokyo"
    When the logistic company tries to update destination to "Medina"
    Then error message is displayed saying journey does not exist

  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step

    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
