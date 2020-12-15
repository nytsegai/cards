package jobsearch.projectutils.psutils;

import jobsearch.framework.logger.Logger;
import java.io.IOException;

public class ApplicationUtility {
    protected static final Logger logger = Logger.getInstance();
    private static final String CARD_SCRIPT = "createCardFilesScript.ps1";


    public static void createCardFiles(String company, String position) {
        try {
            ProcessUtility.executePsScript(CARD_SCRIPT, company, position);
        } catch (IOException e) {
            logger.info(e.toString());
        }
    }

}

