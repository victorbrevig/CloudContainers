
@tag
Feature: Remove client


  Scenario: A logistic company wants to remove a client
  	Given A logistic company
    Given A client with the clientID 1
    When the logistic company removes a client with the clientID 1
    Then the client is deleted with clientID 1
  

  Scenario: A logistic company wants to remove a client
    Given A logistic company
    Given A client with the email "email123@mail.dk"
    When the logistic company removes a client with an email "j1@gmail.com"
    Then the client is deleted with email "j1@gmail.com"
    

  Scenario: A logistic company tries to remove a non existing client
    Given A logistic company
    Given A none existing client
		When the logistic company removes a client with an email "e"
    Then nothing happens

  Scenario: A logistic company tries to remove a non existing client
    Given A logistic company
    Given A none existing client
		When the logistic company removes a client with the clientID 20
    Then nothing happens
    