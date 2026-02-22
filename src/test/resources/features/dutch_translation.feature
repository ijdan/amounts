Feature: Dutch Translation Verification
  As a Spanish speaker or international user
  I want to be able to get numbers translated correctly into Dutch
  So that I can use the same application for my Dutch customers

  Background:
    Given the target translation language is "Dutch"

  # ============================================================
  # Elementary numbers
  # ============================================================

  Scenario Outline: Translating elementary digits
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number | expected_text    |
      | 0      | nul              |
      | 1      | één              |
      | 2      | twee             |
      | 3      | drie             |
      | 4      | vier             |
      | 5      | vijf             |
      | 6      | zes              |
      | 7      | zeven            |
      | 8      | acht             |
      | 9      | negen            |
      | 10     | tien             |
      | 11     | elf              |
      | 12     | twaalf           |
      | 13     | dertien          |
      | 14     | veertien         |
      | 15     | vijftien         |
      | 16     | zestien          |
      | 17     | zeventien        |
      | 18     | achttien         |
      | 19     | negentien        |

  # ============================================================
  # Tens and hundreds
  # ============================================================

  Scenario Outline: Translating tens and hundreds
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number | expected_text     |
      | 20     | twintig           |
      | 21     | eenentwintig      |
      | 22     | tweeëntwintig     |
      | 23     | drieëntwintig     |
      | 24     | vierentwintig     |
      | 28     | achtentwintig     |
      | 30     | dertig            |
      | 33     | drieëndertig      |
      | 40     | veertig           |
      | 45     | vijfenveertig     |
      | 50     | vijftig           |
      | 55     | vijfenvijftig     |
      | 60     | zestig            |
      | 70     | zeventig          |
      | 80     | tachtig           |
      | 81     | eenentachtig      |
      | 90     | negentig          |
      | 99     | negenennegentig   |
      | 100    | honderd           |
      | 101    | honderdéén        |
      | 108    | honderdacht       |
      | 135    | honderdvijfendertig |
      | 200    | tweehonderd       |
      | 256    | tweehonderdzesenvijftig |
      | 999    | negenhonderdnegenennegentig |

  # ============================================================
  # Thousands
  # ============================================================

  Scenario Outline: Translating thousands
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number  | expected_text              |
      | 1000    | duizend                    |
      | 1001    | duizendéén                 |
      | 1010    | duizendtien                |
      | 1100    | elfhonderd                 |
      | 1214    | twaalfhonderdveertien      |
      | 2000    | tweeduizend                |
      | 2024    | tweeduizendvierentwintig   |
      | 3500    | drieduizendvijfhonderd     |
      | 9999    | negenduizendnegenhonderdnegenennegentig |

  # ============================================================
  # Millions
  # ============================================================

  Scenario Outline: Translating millions
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number   | expected_text                                  |
      | 1000000  | één miljoen                                    |
      | 2000000  | twee miljoen                                   |
      | 1500000  | anderhalf miljoen                              |
      | 2500001  | twee miljoen vijfduizendéén                    |
      | 3420000  | drie miljoen vierhonderdtwintigduizend         |

  # ============================================================
  # Billions and larger numbers
  # ============================================================

  Scenario Outline: Translating billions and larger numbers
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number        | expected_text                                  |
      | 1000000000    | één miljard                                    |
      | 2000000000    | twee miljard                                   |
      | 1234567890    | één miljard tweehonderdvierendertig miljoen vijfhonderdzevenenzestigduizend achthonderdnegentig |
      | 1000000000000 | één biljoen                                    |
