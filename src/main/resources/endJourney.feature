
@tag
Feature: End a journey


  Scenario: a journey successfully ended
  	Given A logistic company
    And a valid journey
    And existing client
    And a container owned by the client
    When logistic company tries to put container on journey
    When logistic company tries to end journey
    Then message displayed saying journey successfully ended for 1 containers
    
