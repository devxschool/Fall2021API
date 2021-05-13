package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import db_utils.DBUtils;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.RestHttpRequest;

import static utils.FoodDeliveryEndpoints.CLEAR_CACHE;

public class Hooks {
    
    @Before
    public void setup() throws InterruptedException {
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
