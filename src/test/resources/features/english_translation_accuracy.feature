Feature: English Translation Accuracy
  As a user of the application
  I want to translate numeric values into English text
  So that I get the correct spelled-out word for both simple and complex numbers

  Scenario Outline: Translating basic and foundational numbers
    Given the target translation language is "English"
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number | expected_text       |
      | 0      | Zero                |
      | 2      | Two                 |
      | 10     | Ten                 |
      | 11     | Eleven              |
      | 12     | Twelve              |
      | 13     | Thirteen            |
      | 14     | Fourteen            |
      | 15     | Fifteen             |
      | 16     | Sixteen             |
      | 17     | Seventeen           |
      | 18     | Eighteen            |
      | 19     | Nineteen            |
      | 20     | Twenty              |
      | 21     | Twenty-one          |
      | 22     | Twenty-two          |
      | 30     | Thirty              |
      | 40     | Forty               |
      | 50     | Fifty               |
      | 60     | Sixty               |
      | 70     | Seventy             |
      | 80     | Eighty              |
      | 90     | Ninety              |
      | 99     | Ninety-nine         |
      | 100    | One hundred         |
      | 101    | One hundred and one |
      | 110    | One hundred and ten |

  Scenario Outline: Translating complex and large numbers
    Given the target translation language is "English"
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number         | expected_text                                                                                                            |
      | 1021           | One thousand twenty-one                                                                                                  |
      | 3465           | Three thousand four hundred and sixty-five                                                                               |
      | 12045          | Twelve thousand forty-five                                                                                               |
      | 300041         | Three hundred thousand forty-one                                                                                         |
      | 340543         | Three hundred and forty thousand five hundred and forty-three                                                            |
      | 9456823        | Nine million four hundred and fifty-six thousand eight hundred and twenty-three                                          |
      | 100012334      | One hundred million twelve thousand three hundred and thirty-four                                                        |
      | 3002004001     | Three billion two million four thousand one                                                                              |
      | 1000100010020  | One trillion one hundred million ten thousand twenty                                                                     |
      | 1345476892356  | One trillion three hundred and forty-five billion four hundred and seventy-six million eight hundred and ninety-two thousand three hundred and fifty-six |
      | 12345678901234 | Twelve trillion three hundred and forty-five billion six hundred and seventy-eight million nine hundred and one thousand two hundred and thirty-four         |
