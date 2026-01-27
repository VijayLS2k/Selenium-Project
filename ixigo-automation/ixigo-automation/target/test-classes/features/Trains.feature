Feature: Train selection

  @manualOtp
  Scenario: Select from and to stations
    Given user is logged in manually
    When user opens trains page
    And user selects from station "Salem Jn (SA)"
    And user selects to station "Chennai - All stations(MAS)"
    And user selects departure day 26
	And user clicks search trains
	Then train results should be displayed
    
