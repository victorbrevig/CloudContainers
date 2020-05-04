@tag
Feature: getting number of containers on a journey for a client
  
Background: A logistic company
		Given A logistic company
		

	

  Scenario: getting journey from ID
    Given a valid journey
    When company retrieves journey from ID
    Then the right journey is displayed
  
  Scenario: getting container from ID
    When company retrieves container from ID
    Then the right container is displayed