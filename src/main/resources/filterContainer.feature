@tag
Feature: getting containers for client
  
Background: A logistic company
		Given A logistic company
    And existing client
    And three containers registered to the client
	

  Scenario: journey created succesfully
    When company retrieves containers for client
    Then the relevant containers are displayed