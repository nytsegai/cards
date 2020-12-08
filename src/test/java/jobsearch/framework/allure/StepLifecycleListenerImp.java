package jobsearch.framework.allure;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import jobsearch.framework.logger.Logger;

public class StepLifecycleListenerImp implements StepLifecycleListener {
    protected static final Logger logger = Logger.getInstance(StepLifecycleListenerImp.class);

    @Override
    public void beforeStepStart(StepResult result) {
        logger.info(String.format("allure: %s", result.getName()));
    }

}
