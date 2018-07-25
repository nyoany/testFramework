package testUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory
{
    private static WebDriver webDriver;

    public static WebDriver instantiateDriver()
    {
        if(webDriver == null) {
            String browserName = new TestProperties().getBrowser();
            switch (browserName) {
                case "remote":
//                    ChromeDriverService service = new ChromeDriverService.Builder()
//                            .usingDriverExecutable(new File("D:\\work\\tst\\chromedriver_win32\\chromedriver.exe"))
//                            .usingAnyFreePort()
//                            .build();
//                    try {
//                        service.start();
//                    }
//                    catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    try {
                       // System.setProperty("webdriver.chrome.driver", "D:\\work\\tst\\chromedriver_win32\\chromedriver.exe");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        ChromeOptions options = new ChromeOptions();
                        desiredCapabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
                        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                        webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
                    }
                    catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "C:\\proiecte\\boats\\twli\\chromedriver.exe");
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
