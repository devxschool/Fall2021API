package runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import db_utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.ConfigReader;
import utils.session.AppSession;
import utils.session.LocalSession;
import utils.session.SSHSession;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = {"steps"},
        tags = {"@regression"},
        plugin = {"json:target/cucumber.json"},
        format = {"pretty", "html:target/reports"}
)
public class RegressionRunner {
    private static final Logger LOGGER = LogManager.getLogger(RegressionRunner.class);

    @BeforeClass
    public static void beforeRegression() throws InterruptedException {
        AppSession appSession;
        boolean isSsh = Boolean.parseBoolean(ConfigReader.getPropertiesValue("food.delivery.is.ssh.enabled"));

        if (isSsh) {
            appSession = new SSHSession();
        } else {
            appSession = new LocalSession();
        }
        boolean isAppRunning = appSession.isAppRunning("food_delivery-3.3.1.jar");
        LOGGER.info("is app running? " + isAppRunning);

        Thread thread = new Thread() {
            public void run() {
                if (!isAppRunning) {
                    String didAppStart =
                            appSession.sendCommand("java -jar " + buildDBurl() +" " + buildJarDir());
                }
            }
        };

        thread.start();
        if (!isAppRunning) {
            LOGGER.info("Waiting for 1 minute for the food_delivery to startup");
            Thread.sleep(60000);
        }
    }

    @AfterClass
    public static void afterRegression() {
        DBUtils.close();
    }

    private static String buildDBurl() {
        return "-Dspring.datasource.url=jdbc:mysql://"
                + ConfigReader.getPropertiesValue("food.delivery.db.host")
                + ":" + ConfigReader.getPropertiesValue("food.delivery.db.port.number") + "/"
                + ConfigReader.getPropertiesValue("food.delivery.db.table");
    }

    private static String buildJarDir(){
        return   ConfigReader.getPropertiesValue("food.delivery.home.directory")+"/"
                + ConfigReader.getPropertiesValue("food.delivery.version") + " &";
    }
}
//                String result = appSession.sendCommand("java -jar -Dspring.datasource.url=jdbc:mysql://3.131.35.165:3306/food_delivery_askar 
//                /Users/askarmusakunov/Desktop/FoodDelivery/food_delivery-3.2.1.jar &");