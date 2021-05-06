package utils.session;

public interface AppSession {

    String sendCommand(String str);

    boolean isAppRunning(String appName);

    boolean startJavaApp(String appName);

    boolean stopJavaApp(String appName);
}