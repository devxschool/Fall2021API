package utils.session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalSession implements AppSession {
    private Runtime runtime;

    public LocalSession() {
        this.runtime = Runtime.getRuntime();
    }

    @Override
    public String sendCommand(String command) {
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
        String line = "";
        try {
            Process pr = runtime.exec("pgrep " + appName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            line = reader.readLine();
            if (line != null && !line.equals("giabakubat@MacBook-Pro ~ % ")) {
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
        if (!isAppRunning(appName)) {
            try {
                Process pr = runtime.exec("open -a " + appName);
                if (isAppRunning(appName)) {
                    return true;
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return false;
            }
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