package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestHttpRequest {


    public static RequestSpecification requestSpecification = RestAssured.given();


    public static void addHeaders() {
        //Configurations for every endpoint
        String baseURI = "http://" + ConfigReader.getPropertiesValue("food.delivery.host")+":"+
                ConfigReader.getPropertiesValue("food.delivery.port.number");
        requestSpecification
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
}
