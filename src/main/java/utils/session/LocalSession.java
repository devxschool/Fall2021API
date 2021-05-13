package utils.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalSession implements AppSession {
    private static final Logger LOGGER = LogManager.getLogger(LocalSession.class);

    private Runtime runtime;

    public LocalSession() {
        this.runtime = Runtime.getRuntime();
        LOGGER.info("Running local session");
    }

    @Override
    public String sendCommand(String command) {
        LOGGER.info("Sending " + command);
        String line = "";
        String result = "";
        try {
            Process pr = runtime.exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            return result;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isAppRunning(String appName) {
        LOGGER.info("Checking if app is running local");
        String line = "";
        try {
            Process pr = runtime.exec("ps -ef | grep " + appName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            if (reader.lines().count() > 2) {
                return true;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean startJavaApp(String appName) {
        LOGGER.info("Starting up the app");
            try {
                Process pr = runtime.exec("open -a " + appName);
                if (isAppRunning(appName)) {
                    return true;
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return false;
            }
        return false;
    }

    @Override
    public boolean stopJavaApp(String appName) {
        if (isAppRunning(appName)) {
            try {
                Process pr = runtime.exec("killall " + appName);
                if (!isAppRunning(appName)) {
                    return true;
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return false;
            }
        }
        return false;
    }
}