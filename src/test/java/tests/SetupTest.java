package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.firefox.FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE;


public class SetupTest {

    public WebDriver driver;

    @Parameters({"browser", "baseUrl"})
    @BeforeMethod
    public void init(String browser, String baseUrl) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
            System.setProperty(DRIVER_USE_MARIONETTE, "true");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }


    @AfterMethod
    public void close() {
        driver.close();
    }



}
