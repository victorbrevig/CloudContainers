
@tag
Feature: Adding a client to register

Background: A logistic company
		Given A logistic company


  Scenario: New client succesfully added
    Given A none existing client 
    When Informations is entered
    Then display success message
 

   Scenario: Missing parameter
    Given A none existing client with missing information
    When Informations is entered
    Then missing parameter error message is thrown
 

   Scenario: Email is invalid
    When invalid email "wrongEmail" is entered
    Then invalid email error is displayed for "wrongEmail"
   

   Scenario: Birthdate is invalid
    When invalid birthdate "111-02-1998" is entered
    Then invalid birtdate error is displayed for "111-02-1998"
    
   Scenario: Phone number is invalid
   	When invalid phone number 6969696969696969 is entered
   	Then invalid phone number error is displayed for 6969696969696969
