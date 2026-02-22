Feature: Italian Translation Verification
  As a Spanish speaker or international user
  I want to be able to get numbers translated correctly into Italian
  So that I can use the same application for my Italian customers

  Background:
    Given the target translation language is "Italian"

  # ============================================================
  # Elementary numbers
  # ============================================================

  Scenario Outline: Translating elementary digits
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number | expected_text              |
      | 0      | zero                       |
      | 1      | uno                        |
      | 2      | due                        |
      | 3      | tre                        |
      | 4      | quattro                    |
      | 5      | cinque                     |
      | 6      | sei                        |
      | 7      | sette                      |
      | 8      | otto                       |
      | 9      | nove                       |
      | 10     | dieci                      |
      | 11     | undici                     |
      | 12     | dodici                     |
      | 13     | tredici                    |
      | 14     | quattordici                |
      | 15     | quindici                   |
      | 16     | sedici                     |
      | 17     | diciassette                |
      | 18     | diciotto                   |
      | 19     | diciannove                 |

  # ============================================================
  # Tens and hundreds
  # ============================================================

  Scenario Outline: Translating tens and hundreds
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number | expected_text              |
      | 20     | venti                      |
      | 21     | ventuno                    |
      | 22     | ventidue                   |
      | 23     | ventitré                   |
      | 24     | ventiquattro               |
      | 28     | ventotto                   |
      | 30     | trenta                     |
      | 33     | trentatré                  |
      | 40     | quaranta                   |
      | 45     | quarantacinque             |
      | 50     | cinquanta                  |
      | 55     | cinquantacinque            |
      | 60     | sessanta                   |
      | 70     | settanta                   |
      | 80     | ottanta                    |
      | 81     | ottantuno                  |
      | 90     | novanta                    |
      | 99     | novantanove                |
      | 100    | cento                      |
      | 101    | centouno                   |
      | 108    | centotto                   |
      | 135    | centotrentacinque          |
      | 200    | duecento                   |
      | 256    | duecentocinquantasei       |
      | 300    | trecento                   |
      | 999    | novecentonovantanove       |

  # ============================================================
  # Thousands
  # ============================================================

  Scenario Outline: Translating thousands
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number  | expected_text               |
      | 1000    | mille                       |
      | 1001    | milleuno                    |
      | 1010    | milledieci                  |
      | 1100    | millecento                  |
      | 1214    | milleduecentoquattordici    |
      | 2000    | duemila                     |
      | 2024    | duemilaventiquattro         |
      | 3500    | tremilacinquecento          |
      | 9999    | novemilanovecentonovantanove |

  # ============================================================
  # Millions
  # ============================================================

  Scenario Outline: Translating millions
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number   | expected_text                      |
      | 1000000  | un milione                         |
      | 2000000  | due milioni                        |
      | 1500000  | un milione cinquecentomila         |
      | 2500001  | due milioni cinquecentouno         |
      | 1001000  | un milione mille                   |
      | 3420000  | tre milioni quattrocentoventimila  |

  # ============================================================
  # Billions and beyond
  # ============================================================

  Scenario Outline: Translating billions and larger numbers
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number        | expected_text                                   |
      | 1000000000    | un miliardo                                     |
      | 2000000000    | due miliardi                                    |
      | 1234567890    | un miliardo duecentotrentaquattromilionicinquecentosessantasettemilaottocentonovanta |
      | 1000000000000 | un bilione                                     |  # Optional, rarely used in daily Italian
