
@tag
Feature: Free a container

	Background: 
		Given A logistic company
    And existing client
    And an unowned container

  Scenario: a container is successfully freed
    Given a container is allocated
    When logistic company frees one container
    Then A succes message is displayed for freeing a container


  Scenario: container is on journey
    Given a container is allocated
    And a valid journey
    And logistic company tries to put container on journey
    When logistic company frees one container
    Then A error message is displayed that container is on journey


  Scenario: container is not owned
    Given an unowned container
    When logistic company frees one container
    Then A error message is displayed as container is not owned



