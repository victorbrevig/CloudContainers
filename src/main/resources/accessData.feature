@tag
Feature: access data as client

  Scenario: access existing data
  	Given A logistic company
    And a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey containing "bananas", "milk" and "oranges" respectively
 		And journey is started and run for 10 hours
 		When client request the data for his container
    Then the status of the container is returned
    And a succes for data access is displayed


  Scenario: container is not owned by client 
    Given A logistic company
    And a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And an unowned container
 		When client request the data for a container he does not own
    Then a error message is returned for data access

    Scenario: journey has not started
    Given A logistic company
    And a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey containing "bananas", "milk" and "oranges" respectively
 		When client request the data for his container
    Then a error message is returned for journey has not started
