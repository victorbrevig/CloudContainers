
@tag
Feature: Logistic company search for client


	Background: 
		Given A logistic company

  Scenario: Searching for an existing client
    Given A client with the email "email123@mail.dk"
    When The logistic company searches for "email123@mail.dk"
    Then The client is returned for "email123@mail.dk"
    


  Scenario: Searching for an existing client
    Given A client with the clientID 1
    When The logistic company searches for 1
    Then The client is returned for 1 
    

  Scenario: Searching for a non existing client
    Given A none existing client
    When The logistic company searches for "e"
    Then no client with email "e" exists
 

  Scenario: Searching for a non existing client
    Given A none existing client
    When The logistic company searches for 10
    Then no client with Id 10 exists
