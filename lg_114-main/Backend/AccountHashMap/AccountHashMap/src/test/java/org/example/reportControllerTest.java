package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

import org.example.account.UserRepo;
import org.example.gas.GasInfo;
import org.example.account.accountInfo;
import org.example.report.Report;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class reportControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";


    }

    @Test
    public void  getAllReportsTest () {
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/report/all");

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
    public void  postReportTest () {
        Report rep = new Report();
        rep.setId(1L);
        //rep.setUserID(u);
        //rep.setGasID(5);
        rep.setDate("today");
        rep.setPrice(3.15);

        System.out.println(rep);

        Response response = RestAssured.given().
                contentType("application/json").
                body(rep).
                when().
                post("/report/post/4/15");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void  getByGasTest () {
        Response response = RestAssured.given().
                when().
                get("/report/station/15");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void  delReportTest () {
        Response response = RestAssured.given().
                when().
                delete("/report/delete/all");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void delReportByGasTest  () {
        Response response = RestAssured.given().
                when().
                delete("/report/delete/15");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }
}
