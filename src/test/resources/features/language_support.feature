Feature: Language Support Handling
  As a user of the application
  I want the application to handle both supported (accepted) and unsupported (avoided) languages properly
  So that I get a valid translation for supported languages, and a clear error for unsupported ones

  Scenario Outline: Requesting a translation with a supported language
    Given the application recognizes the language "<language>"
    When the user attempts to process data using the language "<language>"
    Then the user should receive a successfully translated response
    
    Examples:
      | language |
      | English  |
      | French   |
      | Spanish  |
      | Italian  |

  Scenario Outline: Requesting a translation with an unsupported language
    Given the application does not yet recognize the language "<language>"
    When the user attempts to process data using the language "<language>"
    Then the user should see the error message "Language '<code_lang>' is not yet integrated."

    Examples:
      | language   | code_lang |
      | German     | DE        |
      | Portuguese | PT        |
