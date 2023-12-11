package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

import org.example.gas.GasInfo;
import org.example.account.accountInfo;
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
public class AccountControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testGetAllAccounts(){

        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/account");

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
    public void createAccount() throws JSONException {

        //JSONArray json = new JSONArray();
        accountInfo input = new accountInfo();
        input.setId(1L);
        input.setFirstname("max");
        input.setUsername("may");
        input.setUsername("maxmay");
        input.setPassword("max123");


        System.out.println(input);

        Response response = RestAssured.given().
                contentType("application/json").
                body(input).
                when().
                post("/account");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void testLoginCheck(){



        Response response = RestAssured.given().
                post("/account/login/CEOKumNGo/broreally69");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void   testSetPassword(){
        Response response = RestAssured.given().
                put("/account/3/Pa55word");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void  testDeleteByID(){
        Response response = RestAssured.given().
                delete("userDelete/maxmay");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }


    @Test
    public void   testGetFavStations(){

        Response response = RestAssured.given().
                get("/accountInfo/favStations/3/all");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }


    @Test
    public void   testGetAllUserStations(){
        Response response = RestAssured.given().
                get("/accountInfo/3/allStations");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }


    @Test
    public void   deleteFavStation(){

        Response response = RestAssured.given().
                delete("/accountInfo/del/4/favStation/5");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

    }

    @Test
    public void  testPutFavStations(){
        Response response = RestAssured.given().
                put("/accountInfo/4/put/favStation/5");

                        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void  isInFavTest(){
        Response response = RestAssured.given().
                get("/accountInfo/get/isFav/4/5");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }


    @Test
    public void  updateAccountTest(){
        accountInfo input = new accountInfo();
        input.setUsername("max123");
        input.setPassword("alpha");
        input.setLastname("mayer");
        input.setFirstname("maxTest");



        Response response = RestAssured.given().
                contentType("application/json").
                body(input).
                when().
                put("/accountInfo/put/3/station/4");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }




}
