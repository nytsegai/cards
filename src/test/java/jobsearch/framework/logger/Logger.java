package jobsearch.framework.logger;

import io.qameta.allure.Step;

public class Logger {

    private org.apache.logging.log4j.Logger logger;

    private Logger(){
        this(Logger.class);
    }
    private Logger(Class clazz){
        logger = org.apache.logging.log4j.LogManager.getLogger(clazz);
    }

    public static Logger getInstance(){
        return new Logger();
    }

    public static Logger getInstance(Class clazz){
        return new Logger(clazz);
    }

    public void info(Object message){
        logger.info(message);
    }

    public void testStartInfo(Class clazz){
        logger.info(String.format("======= Test '%s' is started =======", clazz.getSimpleName()));
    }

    public void stepInfo(Object message){
        allureInfo(">>>   STEP: " + message);
    }

    @Step("{0}")
    public void allureInfo(Object message){
        logger.info(message);

    }

    public void warn(Object message){
        logger.warn(message);
    }

    @Step("Warn: {0}")
    public void allureWarn(Object message){
        warn(message);
    }

    public void error(Object message){
        logger.error(message);
    }

    @Step("Error: {0}")
    public void allureError(Object message){
        error(message);
    }

    public void fatal(Object message){
        logger.fatal(message);
    }

    @Step("Fatal: {0}")
    public void allureFatal(Object message){
        fatal(message);
    }
}
