package jobsearch.framework.testmanagement;


import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import jobsearch.framework.allure.AllureUtility;
import jobsearch.framework.logger.Logger;

import java.io.File;
import java.util.ArrayList;


/**
 * Test Extension class that allows to intercept test execution after test is finished with custom callback;
 * <p>
 *
 * @author Iryna.Zhukava
 * Created  on 9/7/2018
 */
public class TestRunnerExtension implements AfterEachCallback {

    protected static final Logger logger = Logger.getInstance();


    /**
     * if currently executed test has failed, we read screenshot that is always created,
     * and attach it to the Allure Report;
     *
     * @param context
     */
    @Override
    public void afterEach(ExtensionContext context) {
        Boolean testResult = context.getExecutionException().isPresent();
        ArrayList<String> screenshots = BaseTest.screenShotStorage.get();
        if (testResult) {
            if (!screenshots.isEmpty()) {
                for (String currentTestName : screenshots) {
                    AllureUtility.attachScreenshot(currentTestName);

                }
            }
        }
        for (String currentTestName : screenshots) {
            File screenshot = new File(currentTestName);
            screenshot.delete();

        }


        BaseTest.screenShotStorage.remove();
    }

}




