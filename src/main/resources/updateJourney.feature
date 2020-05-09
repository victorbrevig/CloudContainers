
@tag
Feature: Update Journey

	Background: 
		Given A logistic company

  Scenario: Logistic company wants to update the port of origin of a journey
    Given a registered journey with PoO of "Texas"
    When the logistic company tries to update port of origin to "Cape Town"
    Then poO is updated to "Cape Town"
    

  Scenario: Logistic company wants to update the destination of a journey
    Given a registered journey with destination of "Copenhagen"
    When the logistic company tries to update destination to "Tokyo"
    Then success message is displayed for destination "Tokyo"
    
  
