Feature: Spanish Translation Accuracy
  As a user of the application
  I want to translate numeric values into Spanish text
  So that I get the correct spelled-out word according to Spanish grammar rules

  Scenario Outline: Translating numbers into Spanish
    Given the target translation language is "Spanish"
    When I process the number "<number>"
    Then the translated result should exactly match "<expected_text>"

    Examples:
      | number  | expected_text  |
      | 0       | Cero           |
      | 5       | Cinco          |
      | 15      | Quince         |
      | 17      | Diecisiete     |
      | 20      | Veinte         |
      | 21      | Veintiuno      |
      | 30      | Treinta        |
      | 31      | Treinta y uno  |
      | 100     | Cien           |
      | 101     | Ciento uno     |
      | 500     | Quinientos     |
      | 1000    | Mil            |
      | 2000    | Dos mil        |
      | 21000   | Veintiún mil   |
      | 31000   | Treinta y un mil|
      | 1000000 | Un millón      |
      | 100000  | Cien mil         |
      | 2000000000000 | Dos billones     |
      | 41000000 | Cuarenta y un millones |
      | 21000000 | Veintiún millones |
      | 1000000000 | Un mil millones      |
      | 2000000000 | Dos mil millones  |
