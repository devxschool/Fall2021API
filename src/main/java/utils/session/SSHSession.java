package utils.session;

import com.jcraft.jsch.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SSHSession implements AppSession {
    private Session session;
    private static final String HOST_NAME = "3.20.225.112";
    private static final String USER = "qa";
    private boolean establishConnection() {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(USER, HOST_NAME, 22);
            String privateKey = "/Users/giabakubat/Documents/DevX/devXLinux.pem";
            jsch.addIdentity(privateKey);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            if (session.isConnected()) {
                return true;
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void disconnectSession() {
        session.disconnect();
    }
    @Override
    public String sendCommand(String command) {
        while (!establishConnection()) {
            establishConnection();
        }
        Channel channel;
        String result = "";
        String line;
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream input = channel.getInputStream();
            channel.connect();
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            while ((line = bufferedReader.readLine()) != null) {
                result += line + "\n";
            }
            bufferedReader.close();
            inputReader.close();
            channel.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean isAppRunning(String appName) {
        while (!establishConnection()) {
            establishConnection();
        }
        Channel channel = null;
        boolean isRunning = false;
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("pgrep -i " + appName);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream input = channel.getInputStream();
            channel.connect();
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            while (bufferedReader.readLine() != null) {
                isRunning = true;
            }
            bufferedReader.close();
            inputReader.close();
            channel.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return isRunning;
    }
    @Override
    public boolean startJavaApp(String appName) {
        while (!establishConnection()) {
            establishConnection();
        }
        Channel channel;
        boolean isRunning = false;
        String directory = findAnAppDirectory(appName);
        int index2 = appName.lastIndexOf(".");
        appName = appName.substring(0, index2);
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("cd " + directory + " && java " + appName);
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();
            isRunning = checkJavaApp(appName);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return isRunning;
    }
    @Override
    public boolean stopJavaApp(String appName) {
        Channel channel = null;
        boolean hasStopped = false;
        establishConnection();
        int index2 = appName.lastIndexOf(".");
        appName = appName.substring(0, index2);
        try {
            channel = session.openChannel("shell");
            OutputStream inputstream_of_channel = channel.getOutputStream();
            PrintStream commander = new PrintStream(inputstream_of_channel,
                    true);
            ((ChannelShell) channel).setPty(true);
            channel.connect();
            List<String> ids = idFinder(appName);
            for (String element : ids) {
                commander.print("kill -9 " + element + "\n");
            }
            if (!checkJavaApp(appName)) {
                hasStopped = true;
            }
            commander.close();
            channel.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return hasStopped;
    }
    //Helper Methods
    private String findAnAppDirectory(String appName) {
        while (!establishConnection()) {
            establishConnection();
        }
        Channel channel;
        String line;
        String directory = null;
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("find / -iname " + appName);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream input = channel.getInputStream();
            channel.connect();
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains("Permission denied")) {
                    int index = line.lastIndexOf("/");
                    directory = line.substring(0, index);
                    break;
                }
            }
            bufferedReader.close();
            inputReader.close();
            channel.disconnect();
            System.out.println("I found the dir " + directory);
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return directory;
    }
    public boolean checkJavaApp(String appName) {
        Channel channel = null;
        boolean isRunning = false;
        String line;
        establishConnection();
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("ps -ef | grep " + appName);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream input = channel.getInputStream();
            channel.connect();
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            System.out.println("Checking Java App");
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("java " + appName)) {
                    isRunning = true;
                }
                System.out.println(line);
            }
            channel.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return isRunning;
    }
    private List idFinder(String appName) {
        Channel channel;
        String line;
        establishConnection();
        List<String> ids = new LinkedList<>();
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("ps -ef | grep " + appName);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream input = channel.getInputStream();
            channel.connect();
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            System.out.println("Looking for ids of " + appName);
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("java " + appName)) {
                    String[] array = line.split("\\s+");
                    String id = array[1].trim();
                    ids.add(id);
                }
                System.out.println(line);
            }
            System.out.println("Process ids are " + ids);
            channel.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return ids;
    }
    public void sendFile() {
        Channel channel;
        try {
            channel = session.openChannel("sftp");
            channel.connect();
            ((ChannelSftp) channel).put("/Users/giabakubat/Desktop/devXlogo.png", "/home/qa/MVPS/dev/sprint2");
            channel.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }
    private void whereAmI() {
        while (!establishConnection()) {
            establishConnection();
        }
        Channel channel;
        String line;
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("pwd");
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream input = channel.getInputStream();
            channel.connect();
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            channel.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
    }
}




