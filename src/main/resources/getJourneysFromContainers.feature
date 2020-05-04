@tag
Feature: getting journeys from containers
  
Background: A logistic company
		Given A logistic company
		And a valid journey
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey
	

  Scenario: journey created succesfully
    When company retrieves journeys for client
    Then the relevant journeys are displayed