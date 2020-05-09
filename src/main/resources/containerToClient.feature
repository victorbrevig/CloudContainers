
@tag
Feature: Allocate a container to a client

	Background: 
		Given A logistic company
    And existing client

  Scenario: succesfull allocation
    Given an unowned container
    When a container is allocated
    Then an allocation succes message is displayed


  Scenario: container already owned
    Given an owned container
    When a logistic company tries to allocate container
    Then an allocation failure message is displayed


