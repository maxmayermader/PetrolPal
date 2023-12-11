package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

import org.example.gas.GasInfo;
import org.example.account.accountInfo;
import org.example.reviews.Review;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class reviewControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void   getAllReviewsTest() {
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/review/all");

        String returnString = response.getBody().asString();
        System.out.println(returnString);

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        //String returnString = response.getBody().asString();
        System.out.println(returnString);
        try {
            JSONArray returnArr = new JSONArray(returnString);
            System.out.println(returnArr);
            JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
            //assertEquals("HELLO", returnObj.get("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  makeReviewTest () {
        Review rev = new Review();
        rev.setDescription("balh blah");
        rev.setRating(5.0);

        Response response = RestAssured.given().
                contentType("application/json").
                body(rev).
                when().
                post("/review/post/4/4");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void   getByReviewIDTest() {


        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/review/get/4");


        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void  getUserByReviewIDTest () {



        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/review/user/4");


        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

    }

    @Test
    public void   getStationByReviewIDTest() {



        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/review/station/4");


        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void   getAllReviewsByGasIDTest() {



        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/reviews/station/5");


        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }
}
