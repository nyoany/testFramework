Feature:Google
  Scenario: Google Search
    Given user navigates to https://www.google.ro
    When user searches for james arthur
    Then the results contain the link https://en.wikipedia.org/wiki/James_Arthur
    When the user clicks on the link https://en.wikipedia.org/wiki/James_Arthur
    Then the awards and nominations table contains
      |Year|Organisation|Award|Work|Result|
      |2013|BRIT Awards|British Single of the Year[113]|"Imposssible"|Nominated[114]|

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