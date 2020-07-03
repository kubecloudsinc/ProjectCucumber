Feature: this is to test the sout
  Scenario: This is when the user is printing something
    Given User Says "hi"
    When the system is processing "hi"
    Then the system should say "hi back"