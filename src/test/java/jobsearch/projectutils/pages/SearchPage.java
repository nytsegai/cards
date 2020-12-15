package jobsearch.projectutils.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import jobsearch.framework.testmanagement.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author Nahom Tsegai
 */

public class SearchPage extends Page {
    private final static String CARD_FRAME_ID = "vjs-container-iframe";
    private final static String HOME_PAGE_TITLE_ID = "Indeed Search Page";
    private final static String BROWSER = ConfigManager.BROWSER;
    private final static By SEARCH_FILTER_WHAT_ID = By.id("text-input-what");
    private final static By SEARCH_FILTER_WHERE_ID = By.id("text-input-where");
    private final static By FIND_JOBS_BUTTON_CLASSNAME = By.className("icl-WhatWhere-button");
    private final static By SEARCH_FILTER_TYPE_ID = By.id("filter-job-type");
    private final static By SEARCH_FILTER_DATE_ID = By.id("filter-dateposted");
    private final static By SEARCH_FILTER_TYPE_DROPDOWN_ID = By.className("dd-menu-option");
    private final static By CARD_CLASSNAME = By.className("clickcard");
    private final static By EXPANDED_JOB_CARD_CLASSNAME = By.className("jobsearch-JobComponent");
    private final static By JOB_TITLE_CLASSNAME = By.className("jobtitle");
    private final static By COMPANY_TITLE_CLASSNAME = By.className("company");
    private final static By DATE_POSTED_CLASSNAME = By.className("date");
    private final static By JOB_DESCRIPTION_ID = By.id("jobDescriptionText");
    private final static By NEXT_PAGE_BUTTON = By.cssSelector("[aria-label='Next']");
    private final static By SORT_BY_DATE_ID = By.cssSelector("div.secondRow [rel='nofollow']");
    private final static By CURRENT_PAGE_VALUE_ID = By.cssSelector("[aria-current]");


    public SearchPage() {
        super(HOME_PAGE_TITLE_ID);
        waitForVisibilityLocator(FIND_JOBS_BUTTON_CLASSNAME);
    }

    public boolean isCaptchaDisplayed() {
        return Objects.requireNonNull(title()).contains("solve page");
    }

    public String getCurrentPage() {
        return element(CURRENT_PAGE_VALUE_ID).getText();
    }

    public void sortByDatePosted() {
        clickWhenReady(SORT_BY_DATE_ID);
    }

    public boolean isNextPageButtonDisplayed() {
        List<SelenideElement> elements = elements(NEXT_PAGE_BUTTON);
        return elements.size() > 0;
    }

    public void clickNextPageButton() {
        List<SelenideElement> elements = elements(NEXT_PAGE_BUTTON);
        clickWhenReady(elements.get(0));
    }

    public void filterSearchWhat(String what) {
        clearAndSetText(SEARCH_FILTER_WHAT_ID, what);
    }

    public void filterSearchWhere(String where) {
        clearAndSetText(SEARCH_FILTER_WHERE_ID, where);
    }

    public void clickFindJobButton() {
        clickWhenReady(FIND_JOBS_BUTTON_CLASSNAME);
    }

    public void handlePopup() {
        List<SelenideElement> popup = elements(By.id("popover-foreground"));
        if(popup.size() > 0) {
            clickWhenReady(popup.get(0).$(By.id("popover-x")));
        }
    }

    public void clickFullTimeJobsDropDown() {
        SelenideElement dropdown = element(SEARCH_FILTER_TYPE_ID);
        clickWhenReady(dropdown);
        SelenideElement element = dropdown.$$(SEARCH_FILTER_TYPE_DROPDOWN_ID).get(0);
        clickWhenReady(element);
    }

    public void clickLastDayJobsDropDown() {
        SelenideElement dropdown = element(SEARCH_FILTER_DATE_ID);
        clickWhenReady(dropdown);
        SelenideElement element = dropdown.$$(SEARCH_FILTER_TYPE_DROPDOWN_ID).get(2);
        clickWhenReady(element);
    }

    private void clearAndSetText(By locator, String text) {
        if(BROWSER.equals("chrome")) {
            SelenideElement element = element(locator);
            element.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
            element.sendKeys(text);
        }
        else if(BROWSER.equals("firefox")) {
            setElementValue(locator, text);
        }
    }

    public int getJobCount() {
        return elements(CARD_CLASSNAME).size();
    }

    public void expandCard(int index) {
        SelenideElement card = elements(CARD_CLASSNAME).get(index);
        clickWhenReady(card);
    }

    public String getCardInformation(int index) {
        SelenideElement card = elements(CARD_CLASSNAME).get(index);
        clickWhenReady(card);
        return String.format("| %s | %s | %s |", card.$(JOB_TITLE_CLASSNAME).text(),
                card.$(COMPANY_TITLE_CLASSNAME).text(), card.$(DATE_POSTED_CLASSNAME).text());
    }

    public String getCompanyName(int index) {
        SelenideElement card = elements(CARD_CLASSNAME).get(index);
        return card.$(COMPANY_TITLE_CLASSNAME).text();
    }

    public String getJobTitle(int index) {
        SelenideElement card = elements(CARD_CLASSNAME).get(index);
        return card.$(JOB_TITLE_CLASSNAME).text();
    }

    public boolean isCardExpanded() {
        switchTo().frame(CARD_FRAME_ID);
        if(BROWSER.equals("firefox")) {
            Selenide.sleep(3000);
        }
        int size = elements(EXPANDED_JOB_CARD_CLASSNAME).size();
        switchTo().parentFrame();
        return size > 0;
    }

    public boolean getJobCOVIDInformation() {
        switchTo().frame(CARD_FRAME_ID);
        SelenideElement description = elements(JOB_DESCRIPTION_ID).get(0);
        String text = description.getText();
        switchTo().parentFrame();
        return text.contains("COVID");
    }
}
