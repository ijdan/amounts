Feature: French Translation Accuracy
  As a user of the application
  I want to translate numeric values into French text
  So that I get the correct spelled-out word according to French grammar rules

  Scenario Outline: Translating numbers into French
    Given the target translation language is "French"
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number        | expected_text                                                |
      | 0             | Zéro                                                         |
      | 2             | Deux                                                         |
      | 10            | Dix                                                          |
      | 15            | Quinze                                                       |
      | 17            | Dix-sept                                                     |
      | 18            | Dix-huit                                                     |
      | 19            | Dix-neuf                                                     |
      | 21            | Vingt-et-un                                                  |
      | 22            | Vingt-deux                                                   |
      | 50            | Cinquante                                                    |
      | 51            | Cinquante-et-un                                              |
      | 70            | Soixante-dix                                                 |
      | 71            | Soixante-et-onze                                             |
      | 72            | Soixante-douze                                               |
      | 73            | Soixante-treize                                              |
      | 74            | Soixante-quatorze                                            |
      | 75            | Soixante-quinze                                              |
      | 76            | Soixante-seize                                               |
      | 77            | Soixante-dix-sept                                            |
      | 78            | Soixante-dix-huit                                            |
      | 79            | Soixante-dix-neuf                                            |
      | 80            | Quatre-vingts                                                |
      | 81            | Quatre-vingt-un                                              |
      | 82            | Quatre-vingt-deux                                            |
      | 90            | Quatre-vingt-dix                                             |
      | 91            | Quatre-vingt-onze                                            |
      | 92            | Quatre-vingt-douze                                           |
      | 93            | Quatre-vingt-treize                                          |
      | 94            | Quatre-vingt-quatorze                                        |
      | 95            | Quatre-vingt-quinze                                          |
      | 96            | Quatre-vingt-seize                                           |
      | 97            | Quatre-vingt-dix-sept                                        |
      | 98            | Quatre-vingt-dix-huit                                        |
      | 99            | Quatre-vingt-dix-neuf                                        |
      | 102           | Cent-deux                                                    |
      | 121           | Cent-vingt-et-un                                             |
      | 123           | Cent-vingt-trois                                             |
      | 125           | Cent-vingt-cinq                                              |
      | 280           | Deux-cent-quatre-vingts                                      |
      | 300           | Trois-cents                                                  |
      | 343           | Trois-cent-quarante-trois                                    |
      | 597           | Cinq-cent-quatre-vingt-dix-sept                              |
      | 3597          | Trois-mille-cinq-cent-quatre-vingt-dix-sept                  |
      | 10597         | Dix-mille-cinq-cent-quatre-vingt-dix-sept                    |
      | 80000         | Quatre-vingt-mille                                           |
      | 1000000       | Un-million                                                   |
      | 80000000      | Quatre-vingts-millions                                       |
      | 100000000     | Cent-millions                                                |
      | 200000000     | Deux-cents-millions                                          |
      | 1000000000    | Un-milliard                                                  |
      | 1000000001    | Un-milliard-un                                               |
      | 200000000000  | Deux-cents-milliards                                         |
      | 1003421109    | Un-milliard-trois-millions-quatre-cent-vingt-et-un-mille-cent-neuf |
      | 1000100010020 | Un-billion-cent-millions-dix-mille-vingt                     |

  Scenario Outline: Translating numbers using the application's default fallback language
    Given no target translation language is provided
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number | expected_text |
      | 15     | Quinze        |
      | 100    | Cent          |
