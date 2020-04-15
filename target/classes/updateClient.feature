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
Feature: Update client information

  @tag1
  Scenario: Client wants to update email
  	Given A logistic company
    Given A client with email "smallmoney123@gmail.com"
    When New email entered as "bigmoney123@gmail.com"
    Then Display email update success message

  @tag2
  Scenario: Client wants to update phone number
  	Given A logistic company
    Given A client with phone number 87654321
    When A client with ID 1 New phone number entered as 12345678
    Then Display phonenumber update success message
    
  @tag3
  Scenario: Client wants to update phone number but it is not correct
  	Given A logistic company
    Given A client with phone number 87654321
    When A client with ID 1 New phone number entered as 112
    Then Display error message saying phone number is not correct for 112
