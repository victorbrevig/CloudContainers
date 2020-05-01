
@tag
Feature: elapse time for journey


  Scenario: Elapse time of journey by ten hours
  	Given A logistic company
    Given a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey
    When journey is started and run for 10 hours
    Then journey elapsed time is updated to 10 hours
    And data has been collected up till 10 hours
    And a succes response is given for journey with id 1 and elapsed time 10


  Scenario: journey ends
  	Given A logistic company
    Given a journey from "Copenhagen" to "Oslo" with 5 hours to destination
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey
    When journey is started and run for 10 hours
    Then journey elapsed time is updated to 5 hours
    And data has been collected up till 5 hours
    And a response is returned that the journey has ended for journey 1 after 5 hours
    

  Scenario: no container assigned to journey
    Given A logistic company
    Given a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    When journey is started and run for 10 hours
    Then journey elapsed time is updated to 10 hours
    And no data has been collected
    And a response is returned that the journey has no containers
    

  Scenario: time increment is to small
  	Given A logistic company
    Given a journey from "Copenhagen" to "Oslo" with 80 hours to destination
    And existing client
    And three containers registered to the client
 		And the containers are put on the journey
    When journey is started and run for 0 hours
    Then journey elapsed time is updated to 0 hours
    And no data has been collected
    And a response is given for travel time too low
