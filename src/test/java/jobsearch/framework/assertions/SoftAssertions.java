package jobsearch.framework.assertions;

import org.junit.jupiter.api.Assertions;
import jobsearch.framework.logger.Logger;
import jobsearch.framework.testmanagement.BaseTest;
import jobsearch.projectutils.pages.Page;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class SoftAssertions {

    private static final String ERROR_PATTERN = System.lineSeparator() + "%s" + System.lineSeparator() + "Expected: '%s'." + System.lineSeparator() + "Actual: '%s'" + System.lineSeparator();
    private static final Logger logger = Logger.getInstance(BaseTest.class);
    public static ThreadLocal<ArrayList<String>> store = new ThreadLocal<ArrayList<String>>();

    public static List<String> getErrors() {
        return store.get();
    }

    public static void addError(String message) {
        store.get().add(message);
//        Utils.takeScreenshot((RemoteWebDriver) getWebDriver());
        logger.error(message);
    }

    public static void addError(String expected, String actual, String message) {
        addError(String.format(ERROR_PATTERN, message, expected, actual));
    }

    public static void clearStorage() {
        store.get().clear();

    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            addError(message);
        }
    }


    public static void assertEquals(String expected, String actual, String message) {
        logger.allureInfo("Verify that the string is " + expected);
        if ((expected == null && actual != null) || !expected.equals(actual)) {
            addError(expected, actual, message);
        }
    }
    public static void assertPageTitleEquals(String expected, String actual, String message) {
        logger.allureInfo("Verify that page title is  " + expected);
        if ((expected == null && actual != null) || !expected.equals(actual)) {
            addError(expected, actual, message);
        }
    }
    public static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            addError(Integer.toString(expected), Integer.toString(actual), message);
        }
    }

    public static void shouldContain(String expected, List<String> actualList, String message) {
        if (!actualList.contains(expected)) ;
        addError(expected, String.join(", ", actualList), message);
    }

    public static void shouldNotContain(String expected, List<String> actualList, String message) {
        if (actualList.contains(expected)) ;
        addError(expected, String.join(", ", actualList), message);
    }

    public static void assertFileContentEquals(String pathToExpected, String pathToActual, String message) throws IOException {
        String expectedContent = String.join(File.separator, Files.readAllLines(Paths.get(pathToExpected)));
        String actualContent = String.join(File.separator, Files.readAllLines(Paths.get(pathToActual)));
        assertEquals(expectedContent, actualContent, message);
    }

    public static void pageShouldBeShown(Page page) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("'%s' is not shown, but should.", page.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Page locator: '%s'", page.getLocator()));
        assertTrue(page.isCurrent(), sb.toString());
    }

    public static void pageShouldBeAbsent(Page page) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("'%s' is shown, but should not.", page.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Page locator: '%s'", page.getLocator()));
        assertTrue(page.isCurrent(), sb.toString());
    }

    public static void hardAssertErrorStorage() {
        Assertions.assertTrue(store.get().isEmpty(),
            String.format("During test execution there have found %s errors: %s %s", store.get().size(), System.lineSeparator(), String.join(System.lineSeparator(), store.get())));
    }

    public static void assertColorEquals(Color expected, Color actual, String message) {
        logger.allureInfo("Verify that the element color is " + expected.toString());
        if ((expected == null && actual != null) || !expected.equals(actual)) {
            addError(expected.toString(), actual.toString(), message);

        }
    }
}
