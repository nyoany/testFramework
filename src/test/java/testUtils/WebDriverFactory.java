package testUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class WebDriverFactory
{
    public static WebDriver getDriver()
    {
        String browserName = new TestProperties().getBrowser();
        switch (browserName)
        {
            case "chrome" :
                System.setProperty("webdriver.chrome.driver", "D:\\programe\\chrome driver\\chromedriver_win32\\chromedriver.exe");
                return new ChromeDriver();
            case "ie" : return new InternetExplorerDriver();
            default:
                File firefoxPathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                System.setProperty("webdriver.firefox.bin", firefoxPathBinary.getAbsolutePath());
                return new FirefoxDriver();
        }
    }

    public static void setWebDriver(WebDriver webDriver)
    {

    }
}
