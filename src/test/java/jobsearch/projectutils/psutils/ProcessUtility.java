package jobsearch.projectutils.psutils;

import jobsearch.framework.logger.Logger;
import jobsearch.framework.utils.PathUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.fail;

public class ProcessUtility {
    protected static final Logger logger = Logger.getInstance();

    public static synchronized String executePsScript(String scriptName, String... args) throws IOException {
        StringBuilder response = new StringBuilder();
        StringBuilder request = prepareRequest(scriptName, args);
        Process powerShellProcess = Runtime.getRuntime().exec(request.toString());
        powerShellProcess.getOutputStream().close();
        response.append("Execute powershell script: " + scriptName + System.lineSeparator());
        StringBuilder result = readScriptExecutionResult(response, powerShellProcess);
        getScriptExecutionError(response, powerShellProcess);
        if (!response.toString().contains("Done")) {
            fail("The event for application start is not generated");
        }
        return result.toString();
    }

    private static StringBuilder prepareRequest(String scriptName, String[] args) {
        StringBuilder request = new StringBuilder();
        request.append("powershell.exe  ");
        request.append(PathUtility.getFullFilePath(scriptName) + " ");
        for (String arg : args) {
            request.append(arg + " ");
        }
        System.out.println(request);
        return request;
    }


    private static void getScriptExecutionError(StringBuilder response, Process powerShellProcess) throws IOException {
        String line;
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
            response.append(line + System.lineSeparator());
        }
        stderr.close();
        response.append("Done" + System.lineSeparator());
    }

    private static StringBuilder readScriptExecutionResult(StringBuilder response, Process powerShellProcess) throws IOException {
        String line;
        StringBuilder result = new StringBuilder();
        response.append("Standard Output:" + System.lineSeparator());
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                powerShellProcess.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            if (result.length() > 0)
                result.append(System.lineSeparator());
            response.append(line + System.lineSeparator());
            result.append(line);
        }
        stdout.close();
        response.append("Standard Error:" + System.lineSeparator());
        return result;
    }
}
