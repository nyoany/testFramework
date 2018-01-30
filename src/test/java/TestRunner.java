import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.CucumberFeatureWrapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static testUtils.WebDriverFactory.getDriver;
import static testUtils.WebDriverFactory.instantiateDriver;
import static testUtils.WebDriverFactory.quitDriver;

@CucumberOptions(
            features = "src/test/resources/features",
            plugin = "json:target/cucumber-report.json",
            glue = {"stepdefs"},
            tags = {"~@Ignore"},
            format = {
                    "pretty",
                    "html:target/cucumber-reports/cucumber-pretty",
                    "json:target/cucumber-reports/CucumberTestReport.json",
                    "usage:target/cucumber-usage.json",
                    "rerun:target/cucumber-reports/rerun.txt"
            })
    public class TestRunner {
        private TestNGCucumberRunner testNGCucumberRunner;

        @BeforeClass(alwaysRun = true)
        public void setUpClass() throws Exception {
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }

        @BeforeTest
        public void beforeTests()
        {
            instantiateDriver();
        }

        @Test(description = "Runs Cucumber Feature", dataProvider = "getFeatures")
        public void feature(CucumberFeatureWrapper cucumberFeature) {
            testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
        }

        @DataProvider
        public Object[][] getFeatures() {
            return testNGCucumberRunner.provideFeatures();
        }

        @AfterClass(alwaysRun = true)
        public void tearDownClass() throws Exception {
            quitDriver();
            testNGCucumberRunner.finish();
        }
}
