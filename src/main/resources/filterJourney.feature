
@tag
Feature: Client wish to filter journey

	Background: 
		Given A logistic company
    And three journeys with port of origin "Texas", "Cancun" and "Texas"

  Scenario: client wish to filter journeys after port of origin
    When journeys are filtered for "Texas"
    Then display filtered set of journeys with port of origin "Texas"
    

  Scenario: client wish to filter journeys after destination
    When journeys are filtered for destination "Texas"
    Then display filtered set of journeys with destination "Texas"
    

  Scenario: no journey matching port of destination given by input
    When journeys are filtered for "Roskilde"
    Then get empty set
    

  Scenario: no journey matching destination given by input
    When journeys are filtered for destination "Roskilde"
    Then get empty set
    

