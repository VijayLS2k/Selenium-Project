@manualOtp
Feature: Login OTP

  Scenario: Request OTP to mobile
    Given user opens login
    When user enters mobile number
    And user clicks continue to get OTP
    Then OTP screen should be displayed

    
