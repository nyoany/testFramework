package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by OANY on 12/14/2017.
 */
public class StepDefinitions {

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
