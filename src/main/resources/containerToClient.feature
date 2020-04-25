
@tag
Feature: Allocate a container to a client

  Scenario: succesfull allocation
    Given A logistic company
    And existing client
    And an unowned container
    When a container is allocated
    Then an allocation succes message is displayed


  Scenario: container already owned
    Given A logistic company
    And existing client
    And an owned container
    When a logistic company tries to allocate container
    Then an allocation failure message is displayed


