package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.AddNewFoodToCacheResponseBody;
import domains.CommitToDbResponse;
import domains.Food;
import domains.ResponseErrorMessageBody;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.RestHttpRequest;


import java.util.List;

import static utils.FoodDeliveryEndpoints.*;
import static utils.ValidateFood.validateFood;


public class FoodSteps {
    private Response response;
    Gson gson = new Gson();




    @Given("^add new food to FoodDelivery with the following fields$")
    public void add_new_food_to_FoodDelivery_with_the_following_fields(List<Food> requestPayload) throws JsonProcessingException {
        //used for serializing and deserializing
        //serializing -> converting java to json string
       Food foodFromDataTable =  requestPayload.get(0);
        ObjectMapper objectMapper = new ObjectMapper();
       String json = objectMapper.writeValueAsString(foodFromDataTable);

       System.out.println(json);
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

        validateFood(expectedResponsePayload, actualResponseBody.getFoodCached());
    }


    @Then("^verify response error message \"([^\"]*)\"$")
    public void verifyResponseErrorMessage(String expectedErrorMessage) {
        String actualJson = response.getBody().asPrettyString();
        AddNewFoodToCacheResponseBody actualResponse = gson.fromJson(actualJson, AddNewFoodToCacheResponseBody.class);
        Assert.assertEquals("Create food with missing field error message mismatch",
                expectedErrorMessage, actualResponse.getErrorMessage());
    }


    @When("^users commits the cache to db$")
    public void users_commits_the_cache_to_db()  {
        response =  RestHttpRequest.requestSpecification
                .when()
                .request("POST",COMMIT_TO_DB.getEndpoint());

    }

    @Then("^app should send the following response$")
    public void app_should_send_the_following_response(List<CommitToDbResponse> expectedPayload){
        String actualJson = response.getBody().asPrettyString();
        CommitToDbResponse actualPayload = gson.fromJson(actualJson,CommitToDbResponse.class);
        Assert.assertEquals("Commit to DB invalid number of food saved",expectedPayload.get(0).getNumberOfFoodsSaved(),
                actualPayload.getNumberOfFoodsSaved());
        Assert.assertEquals("Commit to DB invalid message",expectedPayload.get(0).getMessage(),actualPayload.getMessage());
    }
}
