package jobsearch.framework.utils;

import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import jobsearch.framework.logger.Logger;
import jobsearch.framework.testmanagement.BaseTest;
import jobsearch.framework.testmanagement.Constants;
import jobsearch.projectutils.utils.appdata.AppConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Screenshots.getLastScreenshot;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Utils {

    private final static int ATOMIC_TIME_FOR_TIMESTAMP = 1;

    protected static Logger logger = Logger.getInstance(Utils.class);

    public static String getRandomNumbers(int length) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int count = 0; count < length; count++) {
            sb.append(r.nextInt(9));
        }
        return sb.toString();
    }


    public static String getRandomString() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        logger.info(String.format("\nPlaylist name created: %s\n", sb.toString()));
        return sb.toString();
    }

    /**
     * Generates random number in the given range and converts ot to string.
     *
     * @param low lower range of the random value
     * @param high highest range of the random value
     * @return String
     */
    public static String getRandomNumber(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        return Integer.toString(result);
    }

    public static Integer getRandomInt(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    public static byte[] getImageFromFile(String imagefilePath) {
        byte[] byteArray = null;
        try {
            File file = new File(imagefilePath);
            byteArray = FileUtils.readFileToByteArray(file);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return byteArray;
    }

    public static byte[] getImageFromFile() {
        byte[] byteArray = null;
        try {
            File file = getLastScreenshot();
            byteArray = FileUtils.readFileToByteArray(file);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return byteArray;
    }

    public static byte[] getImageFromFile(File file) {
        byte[] byteArray = null;
        try {
            byteArray = FileUtils.readFileToByteArray(file);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return byteArray;
    }

    /**
     * Get value via regex
     *
     * @param regex regex
     * @param text value
     * @return Pair ( 'regex value' , 'key value' )
     */
    public static Pair<String, String> getValueViaRegexGroup(String regex, String text) {
        List<Pair<String, String>> result = getValuesViaRegexGroup(regex, text);
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    /**
     * Get values via regex
     *
     * @param regex regex
     * @param text value
     * @return list of Pair ( 'regex value' , 'key value' )
     */
    public static List<Pair<String, String>> getValuesViaRegexGroup(String regex, String text) {
        List<Pair<String, String>> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            Pair<String, String> pair = new Pair<>(matcher.group(0), matcher.group(1));
            result.add(pair);
        }
        return result;
    }

    public static List<String> getValuesViaRegexGroup(String regex, String group, String text) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result.add(matcher.group(group));
        }
        return result;
    }

    public static synchronized String getCurrentTime(String dateTimeStamp) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeStamp);
        return localDateTime.format(formatter);
    }

    public static synchronized String getUniqueCurrentTime(String dateTimeStamp) throws InterruptedException {
        String result = getCurrentTime(dateTimeStamp);
        Thread.sleep(ATOMIC_TIME_FOR_TIMESTAMP);
        return result;
    }

    public static synchronized String getCurrentTimeWithDayShift(String dateTimeStamp, long dayShift) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE));
        localDateTime = localDateTime.plusDays(dayShift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeStamp);
        return localDateTime.format(formatter);
    }

    /**
     * Loads all properties from the file depending on the set up system profile.
     * If profile hasn't been set up, the default profile will be used.
     *
     * @return Properties object
     */
    public static Properties loadProperties(String propertyPath) {
        Properties props = new Properties();

        try {
            ClassLoader classLoader = Utils.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream(propertyPath);
            props.load(resourceAsStream);
        } catch (IOException e) {

        }

        return props;
    }

    /**
     * Creating screenshot to screenshot folder in the root of the project.
     * If there is an Alert displayed, the standart method does not work,
     * so here there is an option to tal\ke a screenshot using JAVA Robot class.
     * However when tests are run in the multithread - it will take the active window screen not the necessarily the one with the alert.
     */
    public static void takeScreenshot(RemoteWebDriver driver) {
        String destDir = PathUtility.getScreenshotFolder();
        File scrFile = null;

        String fullName = destDir + System.currentTimeMillis() + "." + Constants.IMAGE_EXTENSION;
        try {
            try {
                scrFile = ((RemoteWebDriver) (getWebDriver())).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(fullName));
            } catch (RuntimeException ex) {
                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage capture = new Robot().createScreenCapture(screenRect);
                ImageIO.write(capture, Constants.IMAGE_EXTENSION, new File(fullName));

            }

            BaseTest.screenShotStorage.get().add(fullName);
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }

    }

    public static boolean isDateInIntervalOfDays(String birthday, int daysNumber) {
        SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DAY_PATTERN);
        boolean result = false;
        try {
            Date date1 = sdf.parse(birthday);
            Date date2 = new Date();
            Calendar birth = Calendar.getInstance();
            Calendar current = Calendar.getInstance();

            birth.setTime(date1);
            current.setTime(date2);
            int dayDiff = birth.get(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR);
            if (((0 <= dayDiff) && (dayDiff <= daysNumber)) || (dayDiff == -1)) {
                result = true;
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(AppConstants.DAY_PATTERN);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Generates random number in range {1-9}
     *
     * @return String
     */
    public static String getRandomNumber() {
        int high = 9;
        int low = 1;
        return getRandomNumber(low, high);
    }

    public static String getDayBeforeYesterday() {
        return getDateFromPast(2);
    }

    /**
     * @param days number of days to go in the past
     * @return date that is in the past (minus days from today)
     */
    public static String getDateFromPast(long days) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(AppConstants.DAY_PATTERN);
        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.minusDays(days);
        return dtf.format(localDateTime);
    }

    public static String dateFormatter(String dateOfBirth) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(AppConstants.DAY_PATTERN);
        Date date = null;
        try {
            date = new SimpleDateFormat(AppConstants.DAY_PATTERN).parse(dateOfBirth);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return dtf.format(localDateTime);
    }
}