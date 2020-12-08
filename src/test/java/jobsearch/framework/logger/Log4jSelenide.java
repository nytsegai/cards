package jobsearch.framework.logger;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;

public class Log4jSelenide  implements LogEventListener {

    private final String SELENIDE_PREFIX = "Selenide: ";


    public void onEvent(LogEvent currentLog) {
        Logger.getInstance().info(SELENIDE_PREFIX + currentLog.toString());
    }

//    @Override
    public void afterEvent(LogEvent currentLog) {

    }

//    @Override
    public void beforeEvent(LogEvent currentLog) {

    }
}
