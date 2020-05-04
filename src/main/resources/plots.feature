@tag
Feature: getting data for plots
  

Background: A logistic company
		Given A logistic company
		And existing client
		And a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And three containers registered to the client
 		And the containers are put on the journey
 		And journey is started and run for 10 hours
 		
  Scenario: extract temperature
    When temperature is extracted
    Then an arraylist with the temperature data is returned
    
  Scenario: extract time
    When time is extracted
    Then an arraylist with the time data is returned
    
  Scenario: extract air humidity
    When air humidity is extracted
    Then an arraylist with the air humidity data is returned
    
  Scenario: extract pressure
    When pressure is extracted
    Then an arraylist with the pressure data is returned
    