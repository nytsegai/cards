/**
 * PVM Page Object Base Class
 * This class provides the common functions and properties found in most of the PVM site pages.
 */
package jobsearch.projectutils.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import jobsearch.framework.web.BasePage;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * @author Shawn P. Conlin <sconlin@practicevelocity.com>
 */

@SuppressWarnings("unused")
public class Page extends BasePage {
    public static final String PAGE_TITLE = "PVM > Announcement";
    public static final By PAGE_VISIBILITY_LOCATOR = By.cssSelector("div.mainBody");

    /**
     * Header locators
     */
    protected final static By HDR_MENU = By.id("tblMenuBar");
    protected final static By HDR_LOGOUT = By.partialLinkText("logout");
    protected final static By HDR_MESSAGES = By.partialLinkText("Message(s)");
    protected final static By HDR_URGENTMESSAGES = By.partialLinkText("Urgent Message(s)");

    protected final static By HDR_MENU_PATIENT = By.id("tdMenuBarItemPatient");
    protected final static By HDR_MENU_PATIENT_LOGBOOK = By.xpath("//a[contains(text(),'Log Book')]");
    protected final static By HDR_MENU_PATIENT_SUMMARY = By.xpath("//a[contains(text(),'Summary')]");
    protected final static By HDR_MENU_PATIENT_DEMOGRAPHIC = By.xpath("//a[contains(text(),'Demographic')]");
    protected final static By HDR_MENU_PATIENT_PAYER = By.xpath("//a[contains(text(),'Payer/Insurance')]");
    protected final static By HDR_MENU_PATIENT_RECEIVABLE = By.xpath("//a[contains(text(),'Receivable')]");

    protected final static By HDR_MENU_ADMIN1 = By.id("tdMenuBarItemAdmin1");
    protected final static By HDR_MENU_ADMIN1_CHARGEENTRY = By.xpath("//a[contains(text(),'Charge Entry')]");
    protected final static By HDR_MENU_ADMIN1_PAYMENT = By.xpath("//a[contains(text(),'Payment')]");
    protected final static By HDR_MENU_ADMIN1_FEESCHED = By.xpath("//a[contains(text(),'Fee Schedule')]");
    protected final static By HDR_MENU_ADMIN1_INS = By.xpath("//a[contains(@href,'InsSearch')]");
    protected final static By HDR_MENU_ADMIN1_CORPPROTOCOL = By.xpath("//a[contains(text(),'Corp. Protocol')]");
    protected final static By HDR_MENU_ADMIN1_CHARTREVIEW = By.xpath("//a[contains(text(),'Chart Review')]");
    protected final static By HDR_MENU_ADMIN1_EPSWRKQ = By.xpath("//a[contains(text(),'Occ Med Work Queue')]");

    protected final static By HDR_MENU_ADMIN2 = By.id("tdMenuBarItemAdmin2");
    protected final static By HDR_MENU_ADMIN2_REFUNDS = By.xpath("//a[contains(text(),'RefundsBatch')]");
    protected final static By HDR_MENU_ADMIN2_BILLING = By.xpath("//a[contains(text(),'Billing Tasks')]");
    protected final static By HDR_MENU_ADMIN2_DOCUMENTS = By.xpath("//a[contains(text(),'Documents')]");
    protected final static By HDR_MENU_ADMIN2_WORKLIST = By.xpath("//a[contains(text(),'Work List')]");
    protected final static By HDR_MENU_ADMIN2_MONTHEND = By.xpath("//a[contains(text(),'Month End')]");
    protected final static By HDR_MENU_ADMIN2_REPORTS = By.xpath("//a[contains(text(),'Reports')]");
    protected final static By HDR_MENU_ADMIN2_FIRSTDATA = By.xpath("//a[contains(text(),'FirstData Admin')]");
    protected final static By HDR_MENU_ADMIN2_PATSTATEMENT = By.xpath("//a[contains(text(),'Patient Statements')]");
    protected final static By HDR_MENU_ADMIN2_PATREMINDER = By.xpath("//a[contains(text(),'Patient Reminders')]");
    protected final static By HDR_MENU_ADMIN2_DENIALSMANAGEMENT = By.xpath("//a[contains(text(),'Denials Management')]");

    protected final static By HDR_MENU_RESOURCE = By.id("tdMenuBarItemResource");
    protected final static By HDR_MENU_RESOURCE_DIAGNOSIS = By.xpath("//a[contains(text(),'Diagnosis Search')]");
    protected final static By HDR_MENU_RESOURCE_PROCEDURE = By.xpath("//a[contains(text(),'Procedure Search')]");
    protected final static By HDR_MENU_RESOURCE_CLINIC = By.xpath("//a[contains(text(),'Clinic')]");
    protected final static By HDR_MENU_RESOURCE_PRACTICECLINIC = By.xpath("//a[contains(text(),'Practice/Clinic')]");
    protected final static By HDR_MENU_RESOURCE_PROVIDERINFO = By.xpath("//a[contains(text(),'Provider Search')]");
    protected final static By HDR_MENU_RESOURCE_PRIMARYPHYSINFO = By.xpath("//a[contains(text(),'Primary Physician Info')]");
    protected final static By HDR_MENU_RESOURCE_FACILITY = By.xpath("//a[contains(text(),'Facility')]");
    protected final static By HDR_MENU_RESOURCE_STATUSES = By.xpath("//a[contains(text(),'Statuses/Thresholds')]");


    protected final static By HDR_MENU_PERSONAL = By.id("tdMenuBarItemPersonal");
    protected final static By HDR_MENU_PERSONAL_PROFILE = By.xpath("//a[contains(text(),'Profile')]");
    protected final static By HDR_MENU_SYSTEM = By.id("tdMenuBarItemSystem");
    protected final static By HDR_MENU_SYSTEM_USERS = By.xpath("//a[contains(text(),'User')]");
    protected final static By HDR_MENU_SYSTEM_MENUS = By.xpath("//a[contains(text(),'Menu')]");
    protected final static By HDR_MENU_SYSTEM_MENULIST = By.xpath("//a[contains(text(),'Menu List')]");
    protected final static By HDR_MENU_SYSTEM_UTILITY = By.xpath("//a[contains(text(),'Utility')]");
    protected final static By HDR_MENU_SYSTEM_TOOLS = By.xpath("//a[contains(text(),'Tools')]");

    protected final static By HDR_MENU_SYSTEM_ERRORLOG = By.partialLinkText("App Error Log");
    protected final static By HDR_MENU_SYSTEM_QUICKVISITS = By.partialLinkText("Quick Visits");
    protected final static By HDR_MENU_SYSTEM_RTEPAYORIDS = By.partialLinkText("RTE Payor IDs");

    protected final static By HDR_MENU_HELP = By.id("tdMenuBarItemHelp");
    private static final By HDR_MENU_HELP_19_2 =By.id("tdMenuBarItemHelp_Menu") ;
    protected final static By HDR_MENU_HELP_DOC = By.xpath("//a[contains(text(),'Help Resources')]");;
    protected final static By HDR_MENU_HELP_HOME = By.xpath("//a[contains(text(),'Home Page')]");

    protected final static By HDR_LBL_ERROR = By.id("lblErrorMessage");
    protected final static By HDR_LBL_ERROR2 = By.id("lblErrorMessage"); // class=ErrorMessage2

    // Data Table Locators
    protected final static By PAGE_TBL_DATAROWS = By.xpath("./*/tr[@class='DataGrid-Item' or @class='DataGrid-AlternatingItem' or @class='DataGrid-ItemCentered' or @class='DataGrid-AlternatingItemCentered']");


    // Footer Locators
    protected final static By FTR_TXT_SPAN1 = By.id("FooterDiagnosticsSpan1");
    protected final static By FTR_TXT_SPAN2 = By.id("FooterDiagnosticsSpan2");

    /**
     * Additional Variables
     */
    protected String pageTitle;
    protected String pageHandle;
    protected WebElement HTML;

    public Page() {
        waitForLoad();
        this.pageHandle = getWebDriver().getWindowHandle();
        this.pageTitle = this.getPageTitle();

    }

    public Page(String pageTitle) {
        waitForLoad();
        this.pageHandle = getWebDriver().getWindowHandle();

        // Check that we're on the right page.
        if (!isCurrent(pageTitle)) {
        } else {
            this.pageTitle = pageTitle;
        }
//        logger.info(String.format("Successfully reached %s page.", pageTitle));
    }

    /**
     * Refreshes the current page
     */
    public void refreshPage() {
        refresh();
    }

    /**
     * Returns if the current page has a header menu
     *
     * @return boolean
     */
    public boolean hasHeaderMenu() {
        return element(HDR_MENU).exists();
    }

    /**
     * Determines if this is the current page by comparing the page titles
     *
     * @return
     */
    public boolean isCurrent() {
        return this.isCurrent(pageTitle);
    }

    /**
     * Compares the provided string with the title of the current page to see if
     * they match. Partial matches return as true.
     *
     * @param pgTitle
     * @return boolean
     */
    public Boolean isCurrent(String pgTitle) {
        waitForLoad();
        return getPageTitle().equals(pgTitle);

    }

    /**
     * Returns the title of the current page.
     *
     * @return String
     */
    public String getPageTitle() {

        return getWebDriver().getTitle();
    }

    /**
     * Switches the browser window to the one this page was shown in when it was instantiated.
     */
    public void switchToThisPage() {
        getWebDriver().switchTo().window(pageHandle);
    }

    /**
     * Locates an item on the current page with the id specified by itemName and
     * compares the value to itemValue
     *
     * @param itemName
     * @param itemValue
     * @return boolean
     */
    public boolean verifyItemValue(String itemName, String itemValue) {
        SelenideElement item = element(By.id(itemName));
        return item.getText() == itemValue;
    }

    /**
     * Header functions
     */

    /**
     * Emulates the user clicking the Logout link in the page header
     */
    @Step("Click Log out")
    public void logout() {
        clickWhenReady(HDR_LOGOUT);
    }


    /**
     * Returns the new page object that results from the menu item selected. The
     * mnuTop argument should indicate the text of the main menu option to
     * select. The mnuItem refers to the text of the sub-menu item to be
     * clicked. The menuItem string can be a partial match so long as it can
     * uniquely identify the item.
     *
     * @param mnuTop
     * @param mnuItem
     * @return Page
     */
    protected Page selectMenuItem(String mnuTop, String mnuItem) {

        switch (mnuTop) {
            case "Patient":
                hoverOver(HDR_MENU_PATIENT);
                break;
            case "Admin1":
                hoverOver(HDR_MENU_ADMIN1);
                break;
            case "Admin2":
                hoverOver(HDR_MENU_ADMIN2);
                break;
            case "Resource":
                hoverOver(HDR_MENU_RESOURCE);
                break;
            case "Personal":
                hoverOver(HDR_MENU_PERSONAL);
                break;
            case "System":
                hoverOver(HDR_MENU_SYSTEM);
                break;
            case "Help_Menu":
                hoverOver(HDR_MENU_HELP);
                break;
            default:

        }

        element(By.partialLinkText(mnuItem)).click();
        return new Page();
    }


    /**
     * Footer Functions
     */

    /**
     * Returns the full text of the first footer span
     *
     * @return String
     */
    public String getFooterOne() {
        String footer = element(FTR_TXT_SPAN1).getText();
        return footer;
    }

    /**
     * Returns the server name listed in the first footer span
     *
     * @return
     */
    public String getServer() {
        String footer = element(FTR_TXT_SPAN1).getText();
        String[] entries = footer.split(",");
        String[] server = entries[0].split(":");
        return server[1].trim();
    }

    /**
     * Returns the version name listed in the first footer span
     *
     * @return
     */
    public String getVersion() {
        String footer = element(FTR_TXT_SPAN1).getText();
        String[] entries = footer.split(",");
        String[] version = entries[1].split(":");
        return version[1].trim();
    }

    /**
     * Returns the user name listed in the first footer span if present
     *
     * @return
     */
    public String getUser() {
        String footer = element(FTR_TXT_SPAN1).getText();
        String[] entries = footer.split(",");
        String[] user = entries[2].split(":");
        if (user[0].startsWith("User")) {
            return user[1].trim();
        } else {
            return "";
        }
    }

    /**
     * Returns the program name listed in the first footer span
     *
     * @return
     */
    public String getProgram() {
        String[] program = {"", ""};

        String footer = element(FTR_TXT_SPAN1).getText();
        String[] entries = footer.split(",");
        if (entries.length > 3) {
            program = entries[3].split(":");
        } else {
            program = entries[2].split(":");
        }
        return program[1].trim();
    }

    /**
     * Returns the full text of the second footer span
     *
     * @return String
     */
    public String getFooterTwo() {
        String footer = element(FTR_TXT_SPAN2).getText();
        return footer;
    }

    /**
     * Returns the Practice name listed in the second footer span
     *
     * @return
     */
    public String getFooterPractice() {
        String footer = element(FTR_TXT_SPAN2).getText();
        String[] entries = footer.split(",");
        String[] value = entries[0].split("=");
        return value[1].trim();
    }

    /**
     * Returns the clinic name listed in the second footer span
     *
     * @return
     */
    public String getFooterClinic() {
        String footer = element(FTR_TXT_SPAN2).getText();
        String[] entries = footer.split(",");
        String[] value = entries[1].split("=");
        return value[1].trim();
    }

    /**
     * Returns the service date listed in the second footer span
     *
     * @return
     */
    public String getFooterSvcDate() {
        String footer = element(FTR_TXT_SPAN2).getText();
        String[] entries = footer.split(",");
        String[] value = entries[2].split("=");
        return value[1].trim();
    }

    public boolean isErrorLabelVisible() {
        return element(HDR_LBL_ERROR).isDisplayed();
    }


}
