package steps;

import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import domains.AddNewFoodToCacheResponseBody;
import domains.Food;
import domains.ResponseErrorMessageBody;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.RestHttpRequest;


import java.util.List;

import static utils.FoodDeliveryEndpoints.*;


public class FoodSteps {
    private Response response;
    Gson gson = new Gson();

    @Before
    public void setup(){
        RestHttpRequest.addHeaders();
        response = RestHttpRequest.requestSpecification
        .when()
        .request("POST", CLEAR_CACHE.getEndpoint());

        Assert.assertEquals(200, response.getStatusCode());
    }



    @Given("^add new food to FoodDelivery with the following fields$")
    public void add_new_food_to_FoodDelivery_with_the_following_fields(List<Food> requestPayload) {
        //used for serializing and deserializing
        //serializing -> converting java to json string
       Food foodFromDataTable =  requestPayload.get(0);
       String json = gson.toJson(foodFromDataTable);

       response = RestHttpRequest.requestSpecification
               .body(json)
               .when()
               .request("POST", ADD_FOOD_CACHE.getEndpoint());

    }

    @Then("^verify that status code is (\\d+)$")
    public void verify_that_status_code_is(int expectedStatusCode) {
        Assert.assertEquals("food/cache/add endpoint status code mismatch",
                expectedStatusCode, response.getStatusCode());
    }

    @Then("^verify that food has been successfully added$")
    public void verify_that_food_has_been_successfully_added(List<Food> expectedResponsePayload) {
        //How do you validate response payload?
        //deserialize json payload from response using GSON
        String responsePayload = response.getBody().asPrettyString();
        AddNewFoodToCacheResponseBody actualResponseBody
                = gson.fromJson(responsePayload, AddNewFoodToCacheResponseBody.class);
        Assert.assertEquals("Unexpected number of food created",1,actualResponseBody.getFoodCached().size());
        Assert.assertEquals("Create food description invalid",expectedResponsePayload.get(0).getDescription(),
                 actualResponseBody.getFoodCached().get(0).getDescription());
        Assert.assertEquals("Create food image url invalid", expectedResponsePayload.get(0).getImageUrl(), actualResponseBody.getFoodCached().get(0).getImageUrl());
        Assert.assertEquals("Create food price invalid", expectedResponsePayload.get(0).getPrice(), actualResponseBody.getFoodCached().get(0).getPrice(),0.0);
        Assert.assertEquals("Create food name invalid", expectedResponsePayload.get(0).getName(), actualResponseBody.getFoodCached().get(0).getName());
        Assert.assertEquals("Create food foodtype invalid",expectedResponsePayload.get(0).getFoodType(), actualResponseBody.getFoodCached().get(0).getFoodType());
    }


    @Then("^verify response error message \"([^\"]*)\"$")
    public void verifyResponseErrorMessage(String expectedErrorMessage) {
        String actualJson = response.getBody().asPrettyString();
        ResponseErrorMessageBody actualResponse = gson.fromJson(actualJson, ResponseErrorMessageBody.class);
        Assert.assertEquals("Create food with missing field error message mismatch",
                expectedErrorMessage, actualResponse.getErrorMessage());
    }
}
