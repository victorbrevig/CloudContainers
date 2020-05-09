
@tag
Feature: Remove contianer as client

Background: A logistic company
		Given A logistic company
    And existing client
    And an unowned container

  Scenario: A client removes a container that he/she owns
    When a container is allocated
    When client removes container
    Then  the client is not the owner of the container anymore and has no access

  Scenario: A client removes a container that he/she has access to
		Given an owned container
		When client with container grants other client access to data of container
		When client removes container
		Then client does not have access to the container anymore
