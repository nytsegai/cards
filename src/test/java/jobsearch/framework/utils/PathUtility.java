package jobsearch.framework.utils;

import jobsearch.framework.testmanagement.Constants;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PathUtility {

    public static final String LOC_RESOURCE_BUNDLE = "localization.dictionary";
    public static final String TEST_DATA = "testdata/csvfiles";
    public static final String PATIENT_TEST_DATA = "testdata/csvfiles/patients";

    public static final String URL_DATA_SEPARATOR = "/";
    public static final String DOT = ".";


    public static String getFullFilePath(String fileName) {
        return Paths.get("").toAbsolutePath() + "\\src\\test\\resources\\psscripts\\" + fileName;
    }

    public static Path getFolderPath(String subfolder) throws IOException {
        return getFilePath(subfolder, null);
    }

    public static Path getFilePath(String subfolder, String fileName) throws IOException {
        StringBuilder fileNameWithSubfolder = new StringBuilder();
        if (subfolder != null)
            fileNameWithSubfolder.append(subfolder);
        if (subfolder != null && fileName != null)
            fileNameWithSubfolder.append(URL_DATA_SEPARATOR);
        if (fileName != null)
            fileNameWithSubfolder.append(fileName);

        URL result = PathUtility.class.getClassLoader().getResource(fileNameWithSubfolder.toString());
        if (result == null)
            throw new IOException(String.format("File '%s' not found", fileNameWithSubfolder));
        return Paths.get(result.getPath().substring(1).replace(URL_DATA_SEPARATOR, File.separator));
    }

    public static List<Path> getFilePaths(String subfolder) throws IOException {
        List<Path> result = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(getFolderPath(subfolder))) {
                    paths
                    .filter(Files::isRegularFile)
                    .forEach(x -> result.add(x));
        }
        return result;
    }

    public static Path getTestDataFile(String fileName) throws IOException {
        return getFilePath(TEST_DATA, fileName);
    }

    public static Path getTestDataFile(String clazzName, String extention) throws IOException {
        return getFilePath(PATIENT_TEST_DATA, clazzName + "."+extention);
    }
    public static String getScreenshotFolder() {
        return Paths.get("").toAbsolutePath() + "\\screenshots\\";
    }
    public static String getFullScreenshotPath(String fileName) {
        return getScreenshotFolder() + fileName+"."+Constants.IMAGE_EXTENSION;
    }
    public static String getDriverPath() {
       return Paths.get("").toAbsolutePath()+"\\lib\\IEDriverServer.exe";
    }


}
