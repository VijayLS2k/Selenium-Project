Feature: Train selection

  @manualOtp
  Scenario: Select from and to stations
    Given user is logged in manually
    When user opens trains page
    And user selects from station "Salem Jn (SA)"
    And user selects to station "Chennai - All stations(MAS)"
    And user selects departure day 31
	And user clicks search trains
	And train results should be displayed
    And user enables best available
	And user selects class "3A"
	And user clicks book button
	And user selects saved passenger "Vijay L S"
	Then user confirms passengers
	And user is on checkout page
	And user enters email "vijay@example.com"
	And user selects no free cancellation
	And user clicks proceed to pay
	Then proceed to pay should be visible
    
    
