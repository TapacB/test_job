package tests;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.Test;
import pages.ContactForm;

public class SendMailTest extends SetupTest {

    @Test(priority = 0,
            description = "get mail and send data")
    public void registration() throws EmailException {
        String emailAdr=new ContactForm(driver).contactPopup();
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("first_test_user_mail", "12345678Test"));  //вписать реальный аккаунт
        email.setSSLOnConnect(true);
        email.setFrom("first_test_user_mail@gmail.com");
        email.setSubject("TestMail");
        email.setMsg("Test Mail");
        email.addTo(emailAdr);
        email.send();
    }
}
