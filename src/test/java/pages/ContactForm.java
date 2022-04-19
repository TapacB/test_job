package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactForm {

    public WebDriver driver;
    private String contactForm = "(//a[@class='footer-contacts__link'])[3]";



    public ContactForm(WebDriver driver) {
        this.driver = driver;
    }

    public String contactPopup() {
        return driver.findElement(By.xpath(contactForm)).getText();
    }

}
