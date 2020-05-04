@tag
Feature: getting number of containers on a journey for a client
  
Background: A logistic company
		Given A logistic company
		And a valid journey
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey
	

  Scenario: journey created succesfully
    When company retrieves number of containers on journey for the client
    Then the relevant integer value is displayed