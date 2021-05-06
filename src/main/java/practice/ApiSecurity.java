package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;


public class ApiSecurity {


    @Test
    public void bearerToken() {

        Response response = RestAssured.given()
                .baseUri("https://gorest.co.in/public-api/users")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
//                .header("Authorization","0aff475961a01a697c671c8398e9621b0ca952e4eed595a9e760423448363b78")
                .when()
                .get();

        response.getBody().prettyPrint();
    }


    @Test
    public void bearerToken2() {
        Response response = RestAssured.given()
                .baseUri("https://gorest.co.in/public-api/users")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
//                .auth().basic("Bearer","0aff475961a01a697c671c8398e9621b0ca952e4eed595a9e760423448363b78" )
                .header("Authorization", "Bearer 0aff475961a01a697c671c8398e9621b0ca952e4eed595a9e760423448363b78")
                .body("{\n" +
                        "    \"name\": \"Tenali Ramakrishna\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"email\": \"tenali.ramakrishna@112132325ce.com\",\n" +
                        "    \"status\": \"Active\"\n" +
                        "}")
                .when()
                .post();

        response.getBody().prettyPrint();
    }


    @Test
    public void oAuth() {
        //https://www.googleapis.com/calendar/v3/calendars/askarmusakunov1@gmail.com/events?key=AIzaSyBGZJsJ31miMvq_zBZCy4WjCpq2sz368PQ

        Response response = RestAssured.given()
                .baseUri("https://www.googleapis.com/calendar/v3/calendars/askarmusakunov1@gmail.com/events")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("key","AIzaSyBGZJsJ31miMvq_zBZCy4WjCpq2sz368PQ")
                .auth()
                .oauth2("ya29.a0AfH6SMB6ZSD8ZQhzRda1xd-C4jtDatFRZN0zfhsVDt0bNo_X0oAsPgRhjf7dEfCSArPRC4T7-NBiHGI-oI0qlJfZVMiE2NFGrjOVqurE3B6tSWIUlOj1cgcip0JPRWYYPkKtAgAfEidc6Y49fUBnU12jI-GG")
                .body("{\n" +
                        "     \"summary\": \"Google I/O 2015\",\n" +
                        "     \"description\": \"A chance to hear more about Googles developer products.\",\n" +
                        "       \"location\": \"800 Howard St., San Francisco, CA 94103\",\n" +
                        "\n" +
                        "    \"start\": {\n" +
                        "        \"dateTime\": \"2021-04-26T21:00:00-05:00\",\n" +
                        "        \"timeZone\": \"America/Chicago\"\n" +
                        "  },\n" +
                        "  \"end\": {\n" +
                        "    \"dateTime\": \"2021-04-26T22:00:00-05:00\",\n" +
                        "    \"timeZone\": \"America/Chicago\"\n" +
                        "\n" +
                        "  }\n" +
                        "}")
                .post();

        response.getBody().prettyPrint();
    }
}
//APIs should be protected. 
//Authorization -> bearer token authirzation -> I request an access to some content(Api) and the server(app) has 
//the logic to decide whether you should have access to my api or not. 

//if you meet all of creteria to have access to api then server will grant you a token(cryptic string) which you need
//to include in your api request. 
//some logic to expire this token.
//username and password.


//commit endpoint
//saves all data in cache to db
//10 mins
//5 tc = 50 mins
//db.commit.time.in.min=0.5
//the time was configurable on app side.
//
