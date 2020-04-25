
@tag
Feature: A container has a history log of its journeys, clients and status data

  Scenario: logistic company wants to see history of container
  	Given A logistic company
    And existing client
    And an unowned container
    And a container is allocated
    And the container has been on one journey from "Copenhagen" to "Malmo" and another journey from "Oslo" to "Copenhagen"
		When container history is requested by logistic company
		Then an array containing container journey information is returned
		And response message saying that history of container was successfully retrieved
 

  Scenario: client wants to see history of container owned
  	Given A logistic company
    And existing client
    And an unowned container
    And a container is allocated
    And the container has been on one journey from "Copenhagen" to "Malmo" and another journey from "Oslo" to "Copenhagen"
		When container history is requested by client
		Then an array containing relevant journeys are returned
		And response message saying that history of container was successfully retrieved to client
    

  Scenario: client wants to see history of container owned with no journeys
  	Given A logistic company
    And existing client
    And an unowned container
    And a container is allocated
		When container history is requested by client
		Then an array containing relevant journeys are returned with size 0
		And response message saying that history of container was successfully retrieved to client
		

  Scenario: client wants to see history of container that they do not have access to
  	Given A logistic company
    And existing client
    And an unowned container
		When container history is requested by client
		Then error message shown saying that client does not have access


 