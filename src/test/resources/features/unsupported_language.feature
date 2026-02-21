Feature: Unsupported Language Handing
  As a user of the application
  I want to see an appropriate error message when I request a language that is not recognized
  So that I understand the application cannot process my request

  Scenario Outline: Requesting a translation in an unsupported language
    Given the application does not yet recognize the language "<language>"
    When the user attempts to process data using the language "<language>"
    Then the user should see the error message "Language is not yet integrated."

    Examples:
      | language   |
      | German     |
      | Italian    |
      | Portuguese |
