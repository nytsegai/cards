package tests;

import io.qameta.allure.Step;
import jobsearch.framework.testmanagement.BaseTest;
import jobsearch.projectutils.pages.SearchPage;
import jobsearch.projectutils.utils.appdata.ApplicationConfig;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static jobsearch.framework.utils.Utils.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test")
public class SearchPageTest extends BaseTest {
    private static SearchPage searchPage;
    private static final List<String> cards = new ArrayList<>();
    private final static String WHAT = "\"software quality engineer\" OR \"sqa\" OR" + "\"software quality assurance\" OR \"QA engineer\"";
    private final static String WHERE = "Remote";
    private static String SUBJECT = "Cards for " + getCurrentDate();
    private static String MESSAGE = "";

    @Tag("GetCards")
    @Step("Test")
    @org.junit.jupiter.api.Test
    public void searchPageTest() throws EmailException {
        searchPage = new SearchPage();
        searchPage.filterSearch(WHAT, WHERE);
        searchPage.clickFindJobButton();
        if(!searchPage.isCaptchaDisplayed()) {
            searchPage.clickFullTimeJobsDropDown();
            searchPage.handlePopup();
            searchPage.clickLastDayJobsDropDown();
            searchPage.sortByDatePosted();
            String url =  getWebDriver().getCurrentUrl();
            int cardCount = searchPage.getJobCount();
            int pageCounter = 1;
            assertTrue(cardCount > 0, "No cards available");
            logger.info(String.format("Total Card(s) on Page %s: %s", pageCounter, searchPage.getJobCount()));
            while (searchPage.isNextPageButtonDisplayed()) {
                for (int index = 0; index < cardCount; index++) {
                    searchPage.expandCard(index);
                    String information = searchPage.getCardInformation(index);
                    assertTrue(searchPage.isCardExpanded(), "Card is not expanded");
                    boolean bool = searchPage.getJobCOVIDInformation();
                    if (!bool && !information.contains("Data") && !information.contains("DBA")
                            && !information.contains("Director") && !information.contains("Architect")) {
                        cards.add(information);
                    }
                }
                pageCounter++;
                searchPage.clickNextPageButton();
            }
            if (cards.size() > 0) {
                MESSAGE = String.format("Remote Job(s) Posted Count: %s\n%s", cards.size(), String.join("\n", cards));
            } else {
                MESSAGE = "No jobs recent jobs posted";
            }
            MESSAGE = String.format("%s\n%s", MESSAGE, url);
            logger.info(MESSAGE);
        }
        else {
            SUBJECT = "CARDS - reCAPTCHA";
            MESSAGE = "reCaptcha is displayed. Ending test...";
            logger.info("reCaptcha is displayed. Ending test...");
        }
        sendEmail(ApplicationConfig.HOST, ApplicationConfig.PORT, ApplicationConfig.EMAIL,
                ApplicationConfig.PASSWORD, SUBJECT, MESSAGE);
    }
}
