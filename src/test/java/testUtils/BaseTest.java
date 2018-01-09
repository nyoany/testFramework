package testUtils;

import org.openqa.selenium.WebDriver;

import static testUtils.WebDriverFactory.getDriver;

public class BaseTest
{
    static WebDriver webDriver;

    public static WebDriver getWebDriver()
    {
        if(webDriver == null)
        {
            return getDriver();
        }
        return webDriver;
    }

    public static String getBaseUrl()
    {
        return new TestProperties().getUrl();
    }
}
