package org.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

import org.example.gas.GasInfo;
import org.example.gas.GasRepo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class GasControllerTests {


    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testGetAllAccounts(){
        /*

        Response response = given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/station/all");

        String returnString = response.getBody().asString();
        System.out.println(returnString);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);


        System.out.println(returnString);
        try {
            JSONArray returnArr = new JSONArray(returnString);
            System.out.println(returnArr);
            JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

         */

    }

    @Test
    public void testCreateGas() throws Exception {
        /*
        GasInfo gasInfo = new GasInfo();
        gasInfo.setUsername("TestStation");
        gasInfo.setStationName("TestStationName");
        gasInfo.setPrice(2.5);
        gasInfo.setAddress("TestAddress");
        gasInfo.setDescription("TestDescription");
        gasInfo.setStatus("TestStatus");
        gasInfo.setLon(1.0);
        gasInfo.setLat(2.0);
        gasInfo.setDist(0.0);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(gasInfo)
                .post("/station/post");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        System.out.println(returnString);

        assertEquals("TestStation", response.jsonPath().getString("username"));

         */
        }

@Test
public void testSearchStations() {
        /*
    GasInfo gasInfo = new GasInfo();

    Response response = RestAssured.given()
            .contentType("application/json")
            .body(gasInfo)
            .get("/station/search/5");

    int statusCode = response.getStatusCode();
    assertEquals(200, statusCode);

    String returnString = response.getBody().asString();
    System.out.println(returnString);
    assertEquals("0", response.jsonPath().getString("username"));

         */

}

    @Test
    public void testGetStationDistances() {
        /*
        double lat = 37.7749;
        double lon = -122.4194;

        given()
                .port(port)
                .pathParam("lat", lat)
                .pathParam("lon", lon)
                .when()
                .get("/station/{lat}/{lon}")
                .then()
                .statusCode(200)
                .body("size()", equalTo(12));

         */
    }



    @Test
    public void testSignUp(){

    }


    @Test
    public void  postStation(){
        Response response = RestAssured.given().
                get("/station");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }
    @Test
    public void  idserch(){
        Response response = RestAssured.given().
                get("/station/search/5");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }
    
    @Test
    public void  geo(){
        Response response = RestAssured.given().
                get("/station/4/5");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }





}
