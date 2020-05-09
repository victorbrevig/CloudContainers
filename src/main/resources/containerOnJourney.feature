
@tag
Feature: Add container to journey

	Background: 
		Given A logistic company
    And existing client
    And a valid journey


  Scenario: Successfully add a container to journey
    Given a container owned by the client
    When logistic company tries to put container on journey
    Then success message is displayed
	

  Scenario: Container does not belong to client
    Given an owned container
    When logistic company tries to put container on journey
    Then error message displayed saying that container does not belong to client
    

	
