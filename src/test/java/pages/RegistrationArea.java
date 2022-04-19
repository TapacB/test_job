package pages;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;


public class RegistrationArea {

    public WebDriver driver;
    private String headerLoginButton = "//span[text()='Войти']";
    private String popupRegistationButton = "//button[text()=' Регистрация ']";
    private String authNameInputField = "//input[@name='name']";
    private String authSurnameInputField = "//input[@name='surname']";
    private String authPhoneInputField = "(//span[text()='Юридическое лицо']/following::input)[3]";
    private String authEmailInputField = "//input[@name='email']";
    private String authPassInputField = "//input[@type='password']";
    private String authPassConfirmField = "(//input[@type='password'])[2]";
    private String authLoginField = "(//input[@class='form-input '])[2]";
    private String acceptCheckbox = "input-choice";
    private String submitButton = "//button[text()='Регистрация']";
    private String loginButton = "//button[text()='Войти']";
    private String firstGood = "//button[@class='goods-tile__button goods-tile__button_cart ']";
    private String thirdGood = "(//button[@class='goods-tile__button goods-tile__button_cart '])[3]";
    private String goToCheckoutButton = "//a[@class='button button_one']";
    private String paymentMethodCash = "//span[text()[normalize-space()='Наличными']]";
    private String paymentMethodCard = "//span[text()[normalize-space()='Картой онлайн']]";
    private String submitOrder = "//button[@form='checkout-form']";


    public RegistrationArea(WebDriver driver) {
        this.driver = driver;
    }

    private void openLoginPopup() {
        driver.findElement(By.xpath(headerLoginButton)).click();
    }

    private void clickOnRegistration() {
        driver.findElement(By.xpath(popupRegistationButton)).click();
    }

    private void setName(String name) throws InterruptedException {
        driver.findElement(By.xpath(authNameInputField)).sendKeys(name);
        waitAction(1);
    }

    private void setSurname(String surname) throws InterruptedException {
        driver.findElement(By.xpath(authSurnameInputField)).sendKeys(surname);
        waitAction(1);
    }

    private void setPhone(String phone) throws InterruptedException {
        driver.findElement(By.xpath(authPhoneInputField)).sendKeys(phone);
        waitAction(1);
    }

    private void setEmail(String email) throws InterruptedException {
        driver.findElement(By.xpath(authEmailInputField)).sendKeys(email);
        waitAction(1);
    }

    private void setPassword(String password) throws InterruptedException {
        driver.findElement(By.xpath(authPassInputField)).sendKeys(password);
        waitAction(1);

    }

    private void setPasswordConfirm(String password) throws InterruptedException {
        driver.findElement(By.xpath(authPassConfirmField)).sendKeys(password);
        waitAction(1);
    }

    private void setLogin(String login) throws InterruptedException {
        driver.findElement(By.xpath(authLoginField)).sendKeys(login);
        waitAction(1);
    }

    private void setAcceptCheckbox() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.className(acceptCheckbox)));
        waitAction(1);
    }

    private void addGoodsToBasket(String variant) throws InterruptedException {
        if (variant == "first")
            driver.findElement(By.xpath(firstGood)).click();
        else driver.findElement(By.xpath(thirdGood)).click();

        waitAction(1);
    }

    private void goToCheckout() throws InterruptedException {
        driver.findElement(By.xpath(goToCheckoutButton)).click();
        waitAction(1);
    }

    private void checkPaymentMethod(String variant) throws InterruptedException {
        if (variant == "first")
            driver.findElement(By.xpath(paymentMethodCash)).click();
        else driver.findElement(By.xpath(paymentMethodCard)).click();

        waitAction(1);
    }

    private void submitOrder() {
        driver.findElement(By.xpath(submitOrder)).click();
    }


    //костыль для экономии времени
    //TODO в реальном проекте реализовать нормальные методы для ожидания
    public void waitAction(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    private void clickOnLoginButton() throws InterruptedException {
        driver.findElement(By.xpath(loginButton)).click();
        waitAction(1);
    }

    private void clickOnSubmitButton() {
        driver.findElement(By.xpath(submitButton)).click();
    }


    public void registerAs(String name, String surname, String phone, String email, String password) throws InterruptedException, IOException {
        openLoginPopup();
        clickOnRegistration();
        setName(name);
        setSurname(surname);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
        setPasswordConfirm(password);
        setAcceptCheckbox();
        saveScreenshot("Create user " + name);
        clickOnSubmitButton();
    }

    public void loginAs(String email, String password, String variant) throws InterruptedException, IOException {
        openLoginPopup();
        setLogin(email);
        setPassword(password);
        saveScreenshot("login as " + variant);
        clickOnLoginButton();

    }

    public void buyGood(String variant) throws IOException, InterruptedException {
        addGoodsToBasket(variant);
        saveScreenshot("add " + variant + " good");
        goToCheckout();
        checkPaymentMethod(variant);
        saveScreenshot("check " + variant + " method");
        submitOrder();

    }

    private void saveScreenshot(String name) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src\\test\\resources\\screenshots\\" + name + ".png"));

    }

}
