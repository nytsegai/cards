package jobsearch.framework.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import jobsearch.framework.allure.AllureUtility;
import jobsearch.framework.logger.Logger;
import jobsearch.framework.testmanagement.ConfigManager;
import jobsearch.framework.testmanagement.Constants;
import jobsearch.framework.utils.Utils;
import jobsearch.framework.web.customelement.WebElementsTypes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

public abstract class BasePage {

    protected static final Logger logger = Logger.getInstance(BasePage.class);
    private final String pageName;
    private final By pageVisibilityLocator;

    protected BasePage(By pageVisibilityLocator, String pageName) {
        this.pageVisibilityLocator = pageVisibilityLocator;
        this.pageName = handlePageName(pageName);
    }

    public BasePage() {
        pageName = "";
        pageVisibilityLocator = null;
    }

    public void navigateToBack() {
        getWebDriver().navigate().back();
    }

    public static SelenideElement element(By locator) {
        SelenideElement element = $(locator).shouldBe(Condition.visible);
        try {
            element.scrollIntoView(true);
        } catch (Exception e) {
            element.scrollIntoView(true);

        }
        // }
        return element;
    }

    public static SelenideElement element(String locator) {
        SelenideElement element = $(locator).shouldBe(Condition.visible);
        if (!element.isDisplayed()) {
            element.scrollTo();
        }
        return element;
    }

    public static void makeScreenshot() {
        AllureUtility.attachScreenshot(screenshot(Constants.PREFIX_FOR_PAGE_SCREENSHOT_FILE + Utils.getCurrentTime(Constants.TIMESTAMP_PATTERN_FOR_FILE)));
    }

    private void waitDocIsReady() {
        if (!isIE()) {

            ExpectedCondition<Boolean> pageLoadCondition
                = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            WebDriverWait wait = new WebDriverWait(getWebDriver(), ConfigManager.TIMEOUT);
            wait.until(pageLoadCondition);

        }

    }

    protected void waitForLoad(boolean waitWhenSpinnerIsFinished) {
        waitDocIsReady();

    }

    protected void waitForLoad() {
        waitDocIsReady();
        waitForKnockoutLoad();
        waitForReactLoad();
        // todo: change to analyze ajax
        waitDocIsReady();
        waitForKnockoutLoad();
        waitForReactLoad();
    }

    protected void waitForReactLoad() {
        try {
            $(By.xpath("//*[contains(@class, 'block-ui')]")).waitUntil(hidden, ConfigManager.TIMEOUT);
        } catch (Exception e) {
            throw new TimeoutException("Timeout to load page with the following params: " + System.lineSeparator() + e.getMessage());
        }
    }

    private void waitForKnockoutLoad() {
        $(".loading").waitUntil(hidden, ConfigManager.TIMEOUT);
        $("[class*='globalLoading']").waitUntil(hidden, ConfigManager.TIMEOUT);
    }

    private String handlePageName(String value) {
        if (value == null)
            return getClass().getSimpleName();
        return value;
    }

    public String getName() {
        return pageName;
    }

    public String getLocator() {
        return pageVisibilityLocator.toString();
    }

    /**
     * Field Processing Methods
     */

    /**
     * Finds the provided value in the specified column and returns the row it appears in.
     *
     * @param rowLocator
     * @param columnIndex
     * @param value
     * @return
     */
    public SelenideElement findRowByColValue(By rowLocator, int columnIndex, String value, boolean exact) {
        int foundRow = -1;

        List<SelenideElement> rows = $$(rowLocator);
        for (int row = 0; row < rows.size(); row++) {
            List<SelenideElement> cols = rows.get(row).$$(By.xpath("./td"));
            if (cols.size() > 0) {
                if (exact) {
                    if (cols.get(columnIndex).getText().equals(value.toUpperCase())) {
                        foundRow = row;
                        break;
                    }
                } else {
                    if (cols.get(columnIndex).getText().contains(value.toUpperCase())) {
                        foundRow = row;
                        break;
                    }
                }
            }
        }
        if (foundRow >= 0) {
            return rows.get(foundRow);
        } else {
            return null;
        }
    }

    public List<SelenideElement> getCellsByColName(By rowLocator, String columnName) {
        List<SelenideElement> columns = $$(By.xpath("//*[contains(@class, 'table_head')]//th"));
        int columnIndex = -1;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getText().equalsIgnoreCase(columnName)) {
                columnIndex = i;
            }
            if (columnIndex != -1) break;
        }
        return getCellsByColIndex(rowLocator, columnIndex + 1);
    }

    public List<SelenideElement> getCellsByColIndex(By rowLocator, int columnIndex) {
        List<SelenideElement> cells = new ArrayList<>();
        List<SelenideElement> rows = $$(rowLocator);

        for (SelenideElement selenideElement : rows) {
            List<SelenideElement> cols = selenideElement.$$(By.xpath("./td"));
            if (cols.size() < columnIndex) {
                return null;
            } else {
                cells.add(cols.get(columnIndex - 1));
            }
        }
        if (cells.isEmpty()) {
            return null;
        }
        return cells;
    }

    /**
     * Accepts a locator and a value object and determines the type of field to processed and casts the object to the
     * correct type and handles the field accordingly This function currently supports only HTML 4 input types
     *
     * @param locator
     * @param value
     * @return
     */
    public boolean setElementValue(By locator, Object value, int timeout) {
        boolean success = false;
        if (value != null) {
            try {
                SelenideElement field = element(locator);
                if (null != field) {
                    WebElementsTypes elementType = new WebElementsTypes(field.getTagName().toUpperCase().trim());
                    switch (elementType.getType()) {
                        case INPUT: {
                            WebElementsTypes subType = new WebElementsTypes(field.getAttribute("type").toUpperCase().trim());
                            switch (subType.getType()) {
                                case TEXT:
                                case HIDDEN:
                                case PASSWORD:
                                    if (!(field.getText().equalsIgnoreCase((String) value) || (field.getAttribute("value").equalsIgnoreCase((String) value)))) {
                                        field.setValue((String) value);
                                        success = true;
                                    }
                                    break;
                                case CHECKBOX:
                                    if (field.isSelected() != (boolean) value) {
                                        field.click();
                                    }
                                    success = true;
                                    break;
                                case RADIO:
                                    field.click();
                                    success = true;
                                    break;
                                case BUTTON:
                                case SUBMIT:
                                case RESET:
                                case IMAGE:
                                    field.click();
                                    success = true;
                                    break;
                                case FILE:
                                    break;

                            }
                            break;
                        }
                        case TEXTAREA:
                            field.setValue((String) value);
                            success = true;
                            break;
                        case SELECT:
                            try {
                                Select DDL = new Select(field);
                                if (value.getClass().toString().contains("ArrayList")) {
                                    // TODO - add multiple selection logic for list boxes
                                    if (DDL.isMultiple()) {

                                    } else {
                                        success = false;
                                    }
                                } else {
                                    DDL.selectByVisibleText((String) value);
                                    success = true;
                                }

                            } catch (NoSuchElementException NSEE) {
                                String holder = (String) value;
                                if (holder.equals("") || holder.isEmpty()) {
                                    success = true;
                                }
                                success = false;
                            }
                            break;
                        default:
                            success = false;
                    }

                }
            } catch (StaleElementReferenceException sere) {
                logger.info("loop");
                return setElementValue(locator, value, timeout);
            } catch (TimeoutException toe) {
                success = false;
            } catch (Exception ex) {
                throw ex;
            }
        }
        return success;
    }

    /**
     * Allows for calling the processField function using a default timeout
     *
     * @param locator
     * @param value
     * @return
     */
    public boolean setElementValue(By locator, Object value) {
        return setElementValue(locator, value, Math.toIntExact(ConfigManager.CONDITIONAL_TIMEOUT));
    }

    /**
     * @param locator
     * @see this.clickWhenReady(SelenideElement )
     */
    public void clickWhenReady(By locator) {
        clickWhenReady(element(locator));
        waitForLoad();


    }


    /**
     * Get button, scroll it into view, wait until visible and click.
     * <p> As most of the time we don't expect validation alerts to be displayed on clicking the button
     * we take the screenshot when an alert is displayed and throw error UnhendledAlertException
     * </p>
     *
     * @param button
     */
    public void clickWhenReady(SelenideElement button) {

        if (isIE() || isFirefox()) {
            Selenide.sleep(200);

        }

        button.waitUntil(Condition.visible, ConfigManager.CONDITIONAL_TIMEOUT).click();
        handleValidationAlert();

    }

    /**
     * *Get button, scroll it into view, wait until visible and click.
     * <p> Since sometiomes we expected the validation to be displayed, this method allows us to click the button
     * with the alert expected and without Exception thrown.
     * </p>
     *
     * @param locator
     * @param isAlertExpected
     */
    public void clickWhenReady(By locator, boolean isAlertExpected) {
        clickWhenReady(element(locator), isAlertExpected);


    }

    /**
     * Get button, scroll it into view, wait until visible and click.
     * <p> Since sometiomes we expected the validation to be displayed, this method allows us to click the button
     * with the alert expected and without Exception thrown.
     * </p>
     *
     * @param button
     */
    public void clickWhenReady(SelenideElement button, boolean isAlertExpected) {
        if (isAlertExpected) {
            if (isIE() || isFirefox()) {
                Selenide.sleep(200);

            }
            button.waitUntil(Condition.visible, ConfigManager.CONDITIONAL_TIMEOUT).click();

        } else {
            clickWhenReady(button);
        }


    }

    /**
     * Checks to see if the specified element missing from the page
     *
     * @param locator
     * @return
     */
    public boolean isElementAbsent(By locator) {
        List<SelenideElement> elements = $$(locator);
        return elements.size() <= 0;
    }

    public boolean isTextPresent(By locator, String value, Long timeout) {
        waitForVisibilityLocator(locator, Math.toIntExact(timeout));
        SelenideElement element = element(locator);
        if ($(locator) != null) {
            return value.equalsIgnoreCase(element.getText());
        }

        return false;
    }

    public boolean isTextPresent(By locator, String value) {

        return isTextPresent(locator, value, Long.valueOf(ConfigManager.TIMEOUT));
    }

    /**
     * Checks for an open Alert box
     *
     * @return
     */
    public boolean isAlertPresent() {
        try {
            getWebDriver().switchTo().alert();
            return true;
        } // try
        catch (NoAlertPresentException Ex) {
            return false;
        } // catch
    }

    public String get_SelectedOption_Text(By locator) {
        Select select = new Select(element(locator));
        return select.getFirstSelectedOption().getText();
    }

    public void hoverOver(By locator) throws com.codeborne.selenide.ex.ElementNotFound{
        SelenideElement foundElement = null;
        foundElement = element(locator);
        if (isIE() || isFirefox()) {
            Selenide.sleep(300);

        }
        foundElement.hover();

    }

    public void hoverOver(String cssLocator) {
        SelenideElement foundElement = null;
        foundElement = element(cssLocator);
        foundElement.hover();
    }


    public boolean waitForText(By locator, String text, int timeout) {
        boolean present = false;
        try {
            $(locator).waitUntil(Condition.text(text), timeout);
            return true;
        } catch (TimeoutException tme) {
            present = false;
        }

        return present;
    }


    /**
     * Returns an Alert object if one is present
     *
     * @return
     */
    public Alert getAlert() {
        if (this.isAlertPresent()) {
            return getWebDriver().switchTo().alert();
        } else {
            return null;
        }
    }

    public void waitForVisibilityLocator(By locator) {
        $(locator).waitUntil(Condition.appear, ConfigManager.TIMEOUT);
    }

    public void waitForInvisibilityLocator(By locator, int timeout) {
        $(locator).waitUntil(Condition.hidden, timeout);
    }

    public void waitForVisibilityLocator(By locator, int timeout) {
        $(locator).waitUntil(Condition.appear, timeout);
    }

    /**
     * The locator should be the exact locator of the element in the table.
     * We iterate through found elements and return the index of the row where the element was found
     *
     * @param listOfElementsLocator
     * @param exactName text of the element
     * @return int the number of the row starting from 1
     */
    public int getRowNumberForElementInTable(By listOfElementsLocator, String exactName) {
        List<SelenideElement> workList = $$(listOfElementsLocator);
        int row = -1;
        int iterator = 0;
        while (row == -1) {
            SelenideElement currentElement = workList.get(iterator);
            if (currentElement.getText().equalsIgnoreCase(exactName)) {
                row = 1;
            }
            iterator++;
        }
        return iterator;
    }

    /**
     * When unexpected alert is present we take a screenshot
     * and notify the system to fail the test.
     */
    public void handleValidationAlert() {
        Alert alert;
        if (isAlertPresent()) {
            alert = getAlert();
            String text = "";
            try {
                text = alert.getText();
            } catch (Exception e) {

            }
            Utils.takeScreenshot((RemoteWebDriver) getWebDriver());
            throw new UnhandledAlertException("Unexpected alert was displayed after user has clicked the button." +
                "\n Please, note, the screenshot for this case could be not descriptive in case of multiple threads. \n Alert Text says", text);

        }
    }

    /**
     * Used when there several elements with the same locator, and one of them should contain a specific text.
     * Iterates through all the fields with the locator, and returns true is text is present in one of them.
     *
     * @param tableFieldLocator locator that is common in all the fields we need to check
     * @param value attribute to be present in the fields
     * @return whether text appears in the expected fields
     */
    protected boolean txtPresentInTableField(final By tableFieldLocator, String value) {
        boolean isPresent = false;
        List<SelenideElement> fields = $$(tableFieldLocator);
        SelenideElement field = fields.stream()
            .filter(e -> e.getAttribute("value").equalsIgnoreCase(value))
            .findAny()
            .orElse(null);
        if (field != null) {
            isPresent = true;
        }
        return isPresent;
    }

    /**
     * Used when there several elements with the same locator, and one of them should contain a specific text.
     * Iterates through all the fields with the locator, and returns index of this element in array.
     *
     * @param tableFieldLocator locator that is common in all the fields we need to check
     * @param value attribute to be present in the fields
     * @return index of the matching element or null if element not present.
     */
    protected int indexOfMatchingElementInTable(final By tableFieldLocator, String value) {

        List<SelenideElement> fields = $$(tableFieldLocator);
        List<String> str = fields.stream().map(element -> element.getAttribute("value")).collect(Collectors.toList());
        int first = IntStream.range(0, str.size())
            .filter(i -> value.equals(str.get(i)))
            .findFirst()
            .getAsInt();

        return first;
    }

    /**
     * Verifies that there is value text in the list of the Strings
     *
     * @param options
     * @param value
     * @return true if the value text is present in the list
     */
    protected boolean isSelectOptionPresent(List<String> options, String value) {
        boolean isPresent = false;
        String option = options.stream()
            .filter(e -> e.equalsIgnoreCase(value))
            .findAny()
            .orElse(null);
        if (option != null) {
            isPresent = true;
        }
        return isPresent;
    }

    public Color getColor(By locator) {
        Color statusBG = null;
        String color = "";
        SelenideElement queue = element(locator);
        color = queue.getCssValue("background-color");

        if (color.equalsIgnoreCase("transparent")) {
            statusBG = new Color(0, 0, 0, 0);
        } else {
            if (!isFirefox()) {
                String[] numbers = color.replace("rgba(", "").replace(")", "").split(",");
                statusBG = new Color(Integer.parseInt(numbers[0].trim()), Integer.parseInt(numbers[1].trim()), Integer.parseInt(numbers[2].trim()), Integer.parseInt(numbers[3].trim()));
            } else {
                String[] numbers = color.replace("rgb(", "").replace(")", "").split(",");
                statusBG = new Color(Integer.parseInt(numbers[0].trim()), Integer.parseInt(numbers[1].trim()), Integer.parseInt(numbers[2].trim()), 1);

            }
        }
        System.out.println(statusBG);
        return statusBG;
    }

    public boolean isAngularDivDisabled(By locator) {
        return element(locator).getAttribute("class").contains("disabled");
    }
}
