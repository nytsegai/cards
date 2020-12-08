package jobsearch.framework.testmanagement;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import jobsearch.framework.logger.Logger;

import java.time.Duration;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import static org.junit.platform.launcher.TagFilter.includeTags;

/**
 * Manages test selection by the test tag and the order of the test execution
 * <p>
 *
 * @author Iryna.Zhukava
 * Created  on 10/11/2018
 */
public class TestFactory {
    protected static final Logger logger = Logger.getInstance();
    private static final String TEST_TAG_VARIABLE = "PVM_TEST_TAG";
    private static final String DEFAULT_TAG = "Regression";
    private static final String DEFAULT_TEST_PACKAGE = "jobsearch.projectutils.tests";
    private static final String PVM_TIMEOUT_STRING = "PVM_TIMEOUT";


    public static void beforeTestsSetup() {
        ConfigManager.initializeProperties();
//        try {
//            SQLTestDataCreator.createPatients();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    @Tag("testfactory")
    @Test
    void testFactoryTest() {
        beforeTestsSetup();
        String sysTimeout = System.getenv(PVM_TIMEOUT_STRING);
        if (sysTimeout == null) {
            sysTimeout = ConfigManager.GLOBAL_TIMEOUT_STRING;
        }
        Long globalTimeoutValue = null;
        if (sysTimeout != null && isNotEmpty(sysTimeout)) {
            globalTimeoutValue = Long.valueOf(sysTimeout);
        }
        try {
            if (globalTimeoutValue != null) {
                assertTimeoutPreemptively(Duration.ofMinutes(globalTimeoutValue), () -> executeFullTestSuite(), "Full test execution timeout exceeds " + globalTimeoutValue + " minutes");

            } else {
                executeFullTestSuite();
            }
        } catch (Exception e) {
            logger.info("Caught Unhandled Exception during test run " + e.getMessage());
        } finally {
            if (globalTimeoutValue != null) {
                logger.info("Closing opened drivers");
            }

            cleanUp();
        }

    }

    private void cleanUp() {
//        try {
//            logger.info("Cleaning up after time out");
//            SQLTestDataCreator.deletePatients();
//            SQLTestDataCreator.deleteChargeEntries();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void executeFullTestSuite() {

        String customTag = System.getenv(TEST_TAG_VARIABLE);
        String testTag = DEFAULT_TAG;
        if (isNotEmpty(customTag)) {
            testTag = customTag;
        }
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(
                selectPackage(DEFAULT_TEST_PACKAGE)
            )
            .filters(
                includeTags(testTag)
            )
            .build();
        Launcher launcher = LauncherFactory.create();
        TestExecutionListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }
}
