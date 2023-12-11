package org.example;

import static org.hibernate.cfg.AvailableSettings.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

import org.example.gas.GasInfo;
import org.example.account.accountInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;
//import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class dodControllerTest {

    @LocalServerPort
    int port;
    private WebSocketStompClient webSocketStompClient;


    @BeforeEach
    void setup() {
        this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(
                List.of(new WebSocketTransport(new StandardWebSocketClient()))));
    }


    @Test
    public void    onOpenTest () {

//
//        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
//        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
//        StompSession stompSession = stompClient.connect(URL, new StompSessionHandlerAdapter() {
//        }).get(1, SECONDS);
//
//
//        Response response = RestAssured.given().
//                header("Content-Type", "text/plain").
//                header("charset","utf-8").
//                when().
//                get("/deal/maxmay");
//
//        String returnString = response.getBody().asString();
//        System.out.println(returnString);
//
//        // Check status code
//        int statusCode = response.getStatusCode();
    }

    @Test
    public void     onMessageTest () {

    }

    @Test
    public void    broadcastTest () {

    }

    @Test
    public void   onCloseTest  () {

    }

}
