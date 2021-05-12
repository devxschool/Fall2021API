package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import db_utils.DBUtils;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.RestHttpRequest;
import utils.session.AppSession;
import utils.session.LocalSession;

import static utils.FoodDeliveryEndpoints.CLEAR_CACHE;

public class Hooks {


    @Before(order = 0)
    public void startUpTheApp() {
        Thread thread = new Thread(){
            public void run(){
                AppSession appSession = new LocalSession();
                String result = appSession.sendCommand("java -jar -Dspring.datasource.url=jdbc:mysql://3.131.35.165:3306/food_delivery_askar /Users/askarmusakunov/Desktop/FoodDelivery/food_delivery-3.2.1.jar &");
                System.out.println(result);
            }
        };
        thread.start();
    }

    @Before(order = 1)
    public void setup() throws InterruptedException {
        Thread.sleep(60000);
        RestHttpRequest.addHeaders();
        Response response = RestHttpRequest.requestSpecification
                .when()
                .request("POST", CLEAR_CACHE.getEndpoint());

        Assert.assertEquals(200, response.getStatusCode());
    }

    @After("@development")
    public void cleanUp() {
        DBUtils.close();
    }

}
