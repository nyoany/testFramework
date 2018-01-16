Feature:Test 5
  Scenario: Google Search
    Given user navigates to https://www.google.ro
    When user searches for james arthur
    Then the results contain the link https://en.wikipedia.org/wiki/James_Arthur
    When the user clicks on the link https://en.wikipedia.org/wiki/James_Arthur
    Then the awards and nominations table contains
      |Year|Organisation|Award|Work|Result|
      |2013|BRIT Awards|British Single of the Year[113]|"Impossible"|Nominated[114]|