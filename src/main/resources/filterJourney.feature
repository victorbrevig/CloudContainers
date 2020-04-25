
@tag
Feature: Client wish to filter journey


  Scenario: client wish to filter journeys after port of origin
  	Given A logistic company
    Given three journeys with port of origin "Texas", "Cancun" and "Texas"
    When journeys are filtered for "Texas"
    Then display filtered set of journeys with port of origin "Texas"
    

  Scenario: client wish to filter journeys after destination
  	Given A logistic company
    Given three journeys with destination "Texas", "Cancun" and "Texas"
    When journeys are filtered for destination "Texas"
    Then display filtered set of journeys with destination "Texas"
    

  Scenario: no journey matching port of destination given by input
  	Given A logistic company
    Given three journeys with port of origin "Texas", "Cancun" and "Texas"
    When journeys are filtered for "Roskilde"
    Then get empty set
    

  Scenario: no journey matching destination given by input
  	Given A logistic company
    Given three journeys with destination "Texas", "Cancun" and "Texas"
    When journeys are filtered for destination "Roskilde"
    Then get empty set
    

