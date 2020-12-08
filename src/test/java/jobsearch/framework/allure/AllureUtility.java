package jobsearch.framework.allure;

import io.qameta.allure.Attachment;
import jobsearch.framework.utils.Utils;

import java.io.File;


public class AllureUtility {

    private final static String ALLURE_SCREENSHOT_TYPE = "image/png";
    private final static String ALLURE_SCREENSHOT_PAGE_VALUE = "Page screenshot";
    private final static String ALLURE_ATTACHMENT_PRECONDITION_VALUE = "Precondition data";

    @Attachment(value = ALLURE_ATTACHMENT_PRECONDITION_VALUE)
    public static String attachInfo(String value) {
        return value;
    }

    @Attachment(value = ALLURE_SCREENSHOT_PAGE_VALUE, type = ALLURE_SCREENSHOT_TYPE)
    public static byte[] attachScreenshot(){
        return Utils.getImageFromFile();
    }

    @Attachment(value = ALLURE_SCREENSHOT_PAGE_VALUE, type = ALLURE_SCREENSHOT_TYPE)
    public static byte[] attachScreenshot(String filePath){
        return Utils.getImageFromFile(filePath);
    }
    @Attachment(value = ALLURE_SCREENSHOT_PAGE_VALUE, type = ALLURE_SCREENSHOT_TYPE)
    public static byte[] attachScreenshot(File file){
        return Utils.getImageFromFile(file);
    }
}
