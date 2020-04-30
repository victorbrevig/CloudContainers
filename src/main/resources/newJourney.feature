@tag
Feature: creating a new journey
  I want to use this template for my feature file
Background: A logistic company
		Given A logistic company
	

  Scenario: journey created succesfully
    When journey is created with POO "Copenhagen" and destination "Sydney" and duration 100
    Then journey is created succesfully
  
  Scenario: destination and POO are the same
    When journey is created with POO "Copenhagen" and destination "Copenhagen" and duration 100
    Then following error message is dispayed "Destination cannot be the same as port of origin"
    
  Scenario: duration is out of bounds
  	When journey is created with POO "Copenhagen" and destination "Sydney" and duration 44001
  	Then following error message is dispayed "Duration is out of bounds"

   