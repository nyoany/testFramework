package testUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class WebDriverFactory
{
    private static WebDriver webDriver;

    public static WebDriver instantiateDriver()
    {
        if(webDriver == null) {
            String browserName = new TestProperties().getBrowser();
            switch (browserName) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\onechiforescu\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
                    webDriver = new ChromeDriver();
                    break;
                case "ie":
                    webDriver = new InternetExplorerDriver();
                    break;
                default:
                    File firefoxPathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                    System.setProperty("webdriver.firefox.bin", firefoxPathBinary.getAbsolutePath());
                    webDriver = new FirefoxDriver();
            }
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
        return webDriver;
    }
}
