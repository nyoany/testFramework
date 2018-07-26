import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import static testUtils.WebDriverFactory.instantiateDriver;
import static testUtils.WebDriverFactory.quitDriver;

@CucumberOptions(
            features = "src/test/resources/features",
            glue = {"stepdefs"},
            tags = {"~@Ignore"},
            format = {
                    "pretty",
                    "json:target/cucumber-reports/cucumber.json"
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
