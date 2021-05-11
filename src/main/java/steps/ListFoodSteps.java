package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.Food;
import domains.ListCacheResponse;


import io.restassured.response.Response;
import utils.RestHttpRequest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static utils.FoodDeliveryEndpoints.ADD_FOOD_CACHE;
import static utils.FoodDeliveryEndpoints.LIST_FOOD_CACHE;
import static utils.ValidateFood.validateFood;

public class ListFoodSteps {

    Response response;
    ListCacheResponse actualResponse;
    
    @When("^list food cache request is sent$")
    public void listFoodCacheRequestIsSent() {

        response = RestHttpRequest.requestSpecification
                .when()
                .request("GET", LIST_FOOD_CACHE.getEndpoint());

    }


    @Then("^verify that food cache has the following response$")
    public void verifyThatFoodCacheHasTheFollowingResponse(List<ListCacheResponse> expectedResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = response.getBody().asString();

        actualResponse = objectMapper.readValue(json, ListCacheResponse.class);

        assertEquals("Wrong number of appetizers",expectedResponse.get(0).getNumberOfAppetizers(), actualResponse.getNumberOfAppetizers());
        assertEquals("Wrong number of food in cache",expectedResponse.get(0).getNumberOfFoodsInCache(), actualResponse.getNumberOfFoodsInCache());
        assertEquals("Wrong number of main dishes",expectedResponse.get(0).getNumberOfMainDishes(), actualResponse.getNumberOfMainDishes());
        assertEquals("Wrong number of unknown food",expectedResponse.get(0).getNumberOfUnknownFood(), actualResponse.getNumberOfUnknownFood());
    }

    @Then("^verify the following foods are in the response$")
    public void verifyTheFollowingFoodsAreInTheResponse(List<Food> expectedResponse) {
        validateFood(expectedResponse, actualResponse.getFoodCached());
    }
}