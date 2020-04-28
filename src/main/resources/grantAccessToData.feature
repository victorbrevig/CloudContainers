
@tag
Feature: Title of your feature


  Scenario: access existing data
  	Given A logistic company
    And a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And an owned container
 		And the container is put on the journey containing "bananas"
 		And journey is started and run for 10 hours
 		When client with container grants other client access to data of container
 		And other client tries to get history
 		Then access successfully granted
		And response message saying that history of container was successfully retrieved to client




