package jobsearch.framework.testmanagement;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import jobsearch.framework.assertions.SoftAssertions;
import jobsearch.framework.logger.Logger;
import jobsearch.framework.utils.Utils;
import jobsearch.projectutils.utils.appdata.ApplicationConfig;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

@ExtendWith(TestRunnerExtension.class)
public class BaseTest {
    protected static final Logger logger = Logger.getInstance();
    public static ThreadLocal<ArrayList<String>> screenShotStorage = new ThreadLocal<ArrayList<String>>();

    @BeforeEach
    public void setUpEach() {
        SoftAssertions.store.set(new ArrayList<>());
        screenShotStorage.set(new ArrayList<String>());
        logger.testStartInfo(getClass());
        if (!ApplicationConfig.INITIALIZED) {
            TestFactory.beforeTestsSetup();
        }
        if (isIE()) {
            clearBrowserCache();
        }
        open(ApplicationConfig.FULL_BASE_URL);
        if (isFirefox()) {
            sleep(100);
        }
        if (isChrome()) {
            clearBrowserCache();
        }
        //deleting all cookies in firefox make test run even worse
        getWebDriver().manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (screenShotStorage.get().isEmpty()) {
            Utils.takeScreenshot((RemoteWebDriver) getWebDriver());
        }
        if (isIE() || isFirefox()) {
            WebDriverRunner.closeWebDriver();
            sleep(500);
        }
        close();
        SoftAssertions.hardAssertErrorStorage();
        SoftAssertions.clearStorage();
    }

    public void sendEmail(String host, String port, String emailAddress, String password,
                          String subject, String message) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(host);
        email.setSmtpPort(Integer.parseInt(port));
        email.setAuthenticator(new DefaultAuthenticator(emailAddress, password));
        email.setSSL(true);
        email.setFrom(emailAddress);
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(emailAddress);
        email.send();
    }
}