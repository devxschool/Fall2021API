package practice;

import com.google.gson.Gson;
import domains.AddNewFoodToCacheResponseBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredTests {

    @Before
    public void cleanUp(){
        String url = "http://localhost:8082/food/resetcache";

        Response response = RestAssured.given()
                .baseUri(url)
                .contentType(ContentType.JSON)//accept request body in JSON format
                .accept(ContentType.JSON)
                .when()
                .request("POST");

        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void GET(){


        //RestAssured library is a one of the many
        //Java http clients ->

        //RestAssured uses BDD syntax.
        //given -> headers
        //when -> endpoint, http method type(GET, POST), body
        //then -> validate the response code, and body.

        String listFoodUrl = "http://3.20.225.112:8082/food/cache/list";

        RequestSpecification s;

        Response response = RestAssured
                .given()
                .baseUri(listFoodUrl)
                .contentType(ContentType.JSON)//accept request body in JSON format
                .accept(ContentType.JSON)//accept response body in JSON format
                .when()
                .request("GET");

        Assert.assertEquals(200,response.getStatusCode()); //I identified many existing test cases that were
        //only validating status and not validating response body content.
        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void POST() {

        String url = "http://localhost:8082/food/cache/add";
        String body = "{\n" +
                "   \"description\":\"Lagman\",\n" +
                "   \"imageUrl\": \"https:foods\",\n" +
                "   \"price\": \"25.00\",\n" +
                "   \"name\": \"Lagman\",\n" +
                "   \"foodType\": \"MainDish\"  \n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(url)
                .contentType(ContentType.JSON)//accept request body in JSON format
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .request("POST");

        Assert.assertEquals(200, response.getStatusCode());


        System.out.println(response.getBody().asPrettyString());
        Gson gson = new Gson();
        //fromJson(String json, WhichClass to convert it to?)
        AddNewFoodToCacheResponseBody convertedToJavaObjectResponseBody
                = gson.fromJson(response.getBody().asString(), AddNewFoodToCacheResponseBody.class);

        Assert.assertEquals(1, convertedToJavaObjectResponseBody.getFoodCached().size());
        Assert.assertEquals("Lagman", convertedToJavaObjectResponseBody.getFoodCached().get(0).getDescription());
        Assert.assertEquals(15.0,convertedToJavaObjectResponseBody.getFoodCached().get(0).getPrice(), 0);
    }



}
