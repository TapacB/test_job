package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.RegistrationArea;

import java.io.IOException;

public class RegistrationTest extends SetupTest {


    @DataProvider(name = "Registration")
    public static Object[][] userCredentials() {
        return new Object[][]{
                {"First", "User", "0677777777", "first_test_user_mail@gmail.com", "12345678"},
                {"Second", "Member", "0672222222", "second_test_user_mail@gmail.com", "87654321"},
        };
    }

    @DataProvider(name = "Login")
    public static Object[][] loginCredentials() {
        return new Object[][]{
                {"first_test_user_mail@gmail.com", "12345678", "first"},
                {"second_test_user_mail@gmail.com", "87654321", "second"},
        };
    }

    @Test(dataProvider = "Registration", priority = 0,
            description = "check registration")
    public void registration(String name, String surname, String phone, String email, String password) throws InterruptedException, IOException {
        new RegistrationArea(driver).registerAs(name, surname, phone, email, password);
    }

    @Test(dataProvider = "Login", priority = 1,
            description = "check login and buy button")
    public void loginAndBuy(String email, String password, String variant) throws InterruptedException, IOException {
        RegistrationArea RA = new RegistrationArea(driver);
        String url = driver.getCurrentUrl();
        RA.loginAs(email, password, variant);
        driver.navigate().to(url);
        RA.buyGood(variant);
    }

}
