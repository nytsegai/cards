package jobsearch.framework.testmanagement;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import jobsearch.framework.logger.Log4jSelenide;
import jobsearch.framework.logger.Logger;
import jobsearch.framework.utils.PathUtility;
import jobsearch.framework.utils.Utils;

import java.text.MessageFormat;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ConfigManager {

    private static final String DEFAULT_PROPERTY_BUNLDE = "default";
    private static final String CUSTOM_PROPERTIES_VARIABLE = "PVM_CONFIG";
    private static final MessageFormat PROPERTY_FILE_NAME_PATTERN = new MessageFormat("{0}.properties");
    private static final String SELENIDE_STARTKEY = "selenide.";
    private static final String CUSTOM_BROWSER_VARIABLE = "PVM_BROWSER";
    private static final String CUSTOM_VERSION_VARIABLE = "PVM_VERSION";


    public static boolean INITIALIZED = false;
    public static String FULL_BASE_URL;
    protected static Logger log = Logger.getInstance(Utils.class);
    protected static Properties properties = loadProperties();
    public static final boolean LOG_CONSOLE_VERBOSE = Boolean.parseBoolean(properties.getProperty("log.selenide.verbose"));
    public static final boolean LOG_ALLURE_VERBOSE = Boolean.parseBoolean(properties.getProperty("log.selenide.to.allure.verbose"));
    public static final int TIMEOUT = Integer.parseInt(properties.getProperty("wait.pageTimeout"));
    public static final Long CONDITIONAL_TIMEOUT = Long.parseLong(properties.getProperty("selenide.timeout"));
    public static String BROWSER = properties.getProperty("default.browser");
    public static Boolean HOLD_BROWSER_OPEN =  Boolean.parseBoolean(properties.getProperty("hold.browser.open"));
    public static Boolean HEADLESS =  Boolean.parseBoolean(properties.getProperty("headless"));
    public static Boolean START_MAXIMIZED =  Boolean.parseBoolean(properties.getProperty("start.maximized"));
    public static String GLOBAL_TIMEOUT_STRING = properties.getProperty("globalTimeout");
    public static final String BASE_URL = System.getenv("BASE_URL");

    public static void initializeProperties() {
        INITIALIZED = true;
        initLoggerProperties();
        initUrlProperties();
        initSelenideProperties();
        OperaOptions options = new OperaOptions();
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

    }

    private static void initUrlProperties() {
        String customVersion = System.getenv(CUSTOM_VERSION_VARIABLE);
        FULL_BASE_URL = BASE_URL;
    }

    public static void initLoggerProperties() {
        if (LOG_ALLURE_VERBOSE)
            SelenideLogger.addListener("StepLifecycleListenerImp", new AllureSelenide().screenshots(true).savePageSource(false));
        if (LOG_CONSOLE_VERBOSE)
            SelenideLogger.addListener("Log4jSelenide", new Log4jSelenide());
    }

    public static void initSelenideProperties() {
        String customBrowser = System.getenv(CUSTOM_BROWSER_VARIABLE);
        if (customBrowser != null) {
            BROWSER = customBrowser;
        }
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(SELENIDE_STARTKEY))
                System.setProperty(key, properties.getProperty(key));
        }
        if (BROWSER.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", PathUtility.getDriverPath());
            Configuration.fastSetValue = true;
            Configuration.pageLoadStrategy = "eager";
        }
        Configuration.browser = ConfigManager.BROWSER;
        Configuration.timeout = Long.parseLong(properties.getProperty("selenide.timeout"));
        Configuration.holdBrowserOpen = HOLD_BROWSER_OPEN;
        Configuration.headless = HEADLESS;
        Configuration.startMaximized = START_MAXIMIZED;
    }

    /**
     * Loads all properties from the file depending on the set up system profile.
     * If profile hasn't been set up, the default profile will be used.
     *
     * @return Properties object
     */
    public static Properties loadProperties() {
        Properties props = new Properties();
        String propFileName = PROPERTY_FILE_NAME_PATTERN.format(new Object[]{DEFAULT_PROPERTY_BUNLDE});
        String customProfile = System.getenv(CUSTOM_PROPERTIES_VARIABLE);
        if (isNotEmpty(customProfile)) {
            log.info("Loading profile properties for: " + customProfile);
            propFileName = PROPERTY_FILE_NAME_PATTERN.format(new Object[]{customProfile});
        }
        props = Utils.loadProperties(propFileName);
        return props;
    }
}
