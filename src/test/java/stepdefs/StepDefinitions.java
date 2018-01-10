package stepdefs;

import components.NominationsTable;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import java.util.*;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static testUtils.WebDriverFactory.getDriver;

/**
 * Created by OANY on 12/14/2017.
 */
public class StepDefinitions {

    private int numberOfCows = 0;

    @Given("^user navigates to (.*)$")
    public void userNavigatesTo(String url) throws Throwable {
        getDriver().get(url);
    }

    @When("^user searches for (.*)$")
    public void userSearchesFor(String searchTerm) throws Throwable {
        WebElement googleSearch = getWebDriverWait().until(visibilityOf(getDriver().findElement(By.className("gsfi"))));
        googleSearch.sendKeys(searchTerm);
        googleSearch.sendKeys(Keys.ENTER);
    }

    @When("^the user clicks on the link (.*)$")
    public void theUserClicksOnTheLink(String link) throws Throwable {
        getDriver().findElement(By.cssSelector("a[href = '" + link + "']")).click();
        new WebDriverWait(getDriver(), 5).until(
                visibilityOf(getDriver().findElement(By.className("firstHeading"))));
    }

    @Then("^the results contain the link (.*)$")
    public void theResultsContainTheLink(final String link) throws Throwable {
        getWebDriverWait().until(visibilityOf(getDriver().findElement(By.id("top_nav"))));
        List<WebElement> links = getDriver().findElements(By.cssSelector("._NId .s cite._Rm"));
        Optional<WebElement> foundElement = links.stream().filter(
                element -> element.getText().equals(link)).findFirst();
        assertNotNull(foundElement);
    }

//    @Then("^the awards and nominations table contains$")
//    public void theAwardsAndNominationsTableContains(final DataTable dataTable) throws Throwable {
//        Actions move = new Actions(getDriver());
//        WebElement table = getWebDriverWait().until(visibilityOf(getDriver().findElement(By.cssSelector(".wikitable.plainrowheaders"))));
//        move.moveToElement(table).build().perform();
//        assertEquals(dataTable.raw().get(0), getColumns(table));
//        dataTable.raw().forEach(
//                expectedRow ->
//                {
//                    int rowIndex = dataTable.raw().indexOf(expectedRow);
//                    if(rowIndex == 0)
//                    {
//                        return;
//                    }
//                    assertEquals(expectedRow, getRowsData(table).get(rowIndex));
//                });
//    }

    @Then("^the awards and nominations table contains$")
    public void theAwardsAndNominationsTableContains(List<NominationsTable> nominations) throws Throwable {
        assertTrue(checkSameObjectProperties(nominations.get(0),
                new NominationsTable(getRow(1).get(0), getRow(1).get(1),
                        getRow(1).get(2), getRow(1).get(3), getRow(1).get(4))));
    }

    @Given("^there are (.*) cows$")
    public void thereAreCows(int cows)
    {
        numberOfCows = cows;
    }

    @When("^I count them and add (.*)$")
    public void iCountThemAndAdd(int numberToAdd)
    {
        numberOfCows = numberOfCows + numberToAdd;
    }

    @Then("^the sum is of (.*) cows$")
    public void theSumIsOfCows(int sum)
    {
        AssertJUnit.assertEquals(numberOfCows, sum);
    }

    private boolean checkSameObjectProperties(NominationsTable obj1, NominationsTable obj2)
    {
        return ((obj1.getAward().equals(obj2.getAward()))
                && (obj1.getOrganisation().equals(obj2.getOrganisation()))
                && (obj1.getResult().equals(obj2.getResult()))
                && (obj1.getWork().equals(obj2.getWork()))
                && (obj1.getYear().equals(obj2.getYear())));
    }

    private List<String> getRow(int index)
    {
        Actions move = new Actions(getDriver());
        WebElement table = getWebDriverWait().until(visibilityOf(getDriver().findElement(By.cssSelector(".wikitable.plainrowheaders"))));
        move.moveToElement(table).build().perform();
        return getRowsData(table).get(index);
    }

    private List<String> getColumns(WebElement table)
    {
        List<WebElement> columns = table.findElements(By.cssSelector("th"));
        return columns.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private Map<Integer, List<String>> getRowsData(WebElement table)
    {
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));
        Map<Integer, List<String>> rowsAndData = new HashMap<>();
        rows.forEach(
                row ->
                {
                    rowsAndData.put(rows.indexOf(row),
                            row.findElements(By.cssSelector("td")).stream().map(WebElement::getText).collect(Collectors.toList()));
                }
        );
        return rowsAndData;
    }

    private WebDriverWait getWebDriverWait()
    {
        return new WebDriverWait(getDriver(), 5);
    }

    @Given("user goes")
    public void user_enters_testuser__and_Test(DataTable userCredentials) throws Throwable {

        Map<String,List<String>> actualValues = getKeysAndValuesFromSkillsSection();
        List<List<String>> data = userCredentials.raw();
        // all the data
        Map<String, List<String>> expectedValues = getTableDataColumns(data);

        expectedValues.forEach((key, value )->
            assertEquals(actualValues.get(key), expectedValues.get(key)));
    }

     public Map<String,List<String>> getKeysAndValuesFromSkillsSection()
     {
         Map<String,List<String>> map = new LinkedHashMap<>();
         return map;
     }

    private Map<String, List<String>> getTableDataColumns(List<List<String>> data) {
        Map<String, List<String>> columnsAndData = new HashMap<>();
        // get columns
        List<String> columns = data.get(0);
        // for all the rows cells relative to the column index
        columns.forEach(
                column ->
                {
                    List<String> columnData = new ArrayList<>();
                    data.forEach(
                            row ->
                            {
                                // if the row is the columns names, skip it
                                if(data.indexOf(row) == 0)
                                {
                                    return;
                                }
                                // else add row data for the column index
                                columnData.add(row.get(columns.indexOf(column)));
                            });
                    // for each column put the column name and the column data
                    columnsAndData.put(column, columnData);
                }
        );
        return columnsAndData;
    }


//    @When("^doing$")
//    public void doing(DataTable usercredentials) throws Throwable {
//
//        //Write the code to handle Data Table
//        List<List<String>> data = usercredentials.raw();
//
//    }
//
//
//    @Then("^done$")
//    public void done(DataTable usercredentials) throws Throwable {
//
//        //Write the code to handle Data Table
//        List<List<String>> data = usercredentials.raw();
//
//    }
}
