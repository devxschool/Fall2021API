package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredDemo {

    public static void main(String[] args) {
            
        String url = "http://3.20.225.112:8082/food/cache/add";

        String body = "{\n" +
                "   \"description\":\"Lagman\",\n" +
                "   \"imageUrl\": \"https:foods\",\n" +
                "   \"price\": \"15.00\",\n" +
                "   \"name\": \"Lagman\",\n" +
                "   \"foodType\": \"MainDish\"  \n" +
                "}";

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

        System.out.println(response.getStatusCode());//200
        System.out.println(response.getBody().asPrettyString());


    }
}
