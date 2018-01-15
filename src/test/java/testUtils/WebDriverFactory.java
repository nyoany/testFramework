package testUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class WebDriverFactory
{
    private static WebDriver webDriver;

    public static WebDriver instantiateDriver()
    {
        String browserName = new TestProperties().getBrowser();
        switch (browserName)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\onechiforescu\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                webDriver = new ChromeDriver(options);
                break;
            case "ie":
                webDriver = new InternetExplorerDriver();
                break;
            default:
                File firefoxPathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                System.setProperty("webdriver.firefox.bin", firefoxPathBinary.getAbsolutePath());
                webDriver = new FirefoxDriver();
        }
        return webDriver;
    }

    public static void quitDriver()
    {
        if(webDriver != null)
        {
            webDriver.quit();
        }
    }

    public static WebDriver getDriver()
    {
        if(webDriver == null)
        {
            instantiateDriver();
        }
        return webDriver;
    }
}
