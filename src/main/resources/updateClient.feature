
@tag
Feature: Update client information


	Background: 
		Given A logistic company

  Scenario: Client wants to update email
    Given A client with email "smallmoney123@gmail.com"
    When New email entered as "bigmoney123@gmail.com"
    Then Display email update success message


  Scenario: Client wants to update phone number
    Given A client with phone number 87654321
    When A client with ID 1 New phone number entered as 12345678
    Then Display phonenumber update success message
    

  Scenario: Client wants to update phone number but it is not correct
    Given A client with phone number 87654321
    When A client with ID 1 New phone number entered as 112
    Then Display error message saying phone number is not correct for 112
