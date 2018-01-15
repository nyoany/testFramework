Feature:Calc 5
  Scenario: Calculations
  Scenario Outline: calculating things
    Given there are <cows> cows
    When I count them and add <add>
    Then the sum is of <total> cows

    Examples:
      |cows|add|total|
      |12|3|15|
      |4|4|8|
      |1|1|2|
      |5|15|20|