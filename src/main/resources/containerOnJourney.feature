
@tag
Feature: Add container to journey


  Scenario: Successfully add a container to journey
  	Given A logistic company
    Given existing client
    And a container owned by the client
    And a valid journey
    When logistic company tries to put container on journey
    Then success message is displayed
	

  Scenario: Container does not belong to client
    Given A logistic company
    Given existing client
    And an owned container
    And a valid journey
    When logistic company tries to put container on journey
    Then error message displayed saying that container does not belong to client
    

	
