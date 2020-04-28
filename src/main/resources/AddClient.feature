
@tag
Feature: Adding a client to register


  Scenario: New client succesfully added
    Given A logistic company
    Given A none existing client 
    When Informations is entered
    Then display success message
 

   Scenario: Missing parameter
    Given A logistic company
    Given A none existing client with missing information
    When Informations is entered
    Then missing parameter error message is thrown
 

   Scenario: Email is invalid
    Given A logistic company
    When invalid email "wrongEmail" is entered
    Then invalid email error is displayed for "wrongEmail"
   

   Scenario: Birthdate is invalid
    Given A logistic company
    When invalid birthdate "111-02-1998" is entered
    Then invalid birtdate error is displayed for "111-02-1998"
