package jobsearch.projectutils.utils.appdata;

import jobsearch.framework.testmanagement.ConfigManager;

public class ApplicationConfig extends ConfigManager {

    public static String HOST = System.getenv("HOST");
    public static String PORT = System.getenv("PORT");
    public static String EMAIL = System.getenv("EMAIL");
    public static String PASSWORD = System.getenv("PASSWORD");
    public static String SEARCH_WINDOW = System.getenv("SEARCH_WINDOW");
}
