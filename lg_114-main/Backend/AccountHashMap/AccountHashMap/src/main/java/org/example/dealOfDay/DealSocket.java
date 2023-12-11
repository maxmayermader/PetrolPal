package org.example.dealOfDay;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@Controller
@ServerEndpoint(value = "/deal/{stationName}", configurator = DealSocketConfigurator.class)
public class DealSocket {

    private static DealRepo dealRepo;

    @Autowired
    public void setDealRepo(DealRepo repo){this.dealRepo = repo;}

    private static Map<Session, String> sessionMap = new Hashtable<>();
    private static Map<String, Session> stattionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(DealSocketConfigurator.class);



    @OnOpen
    public void onOpen(Session session, @PathParam("stationName") String station)
            throws IOException {

        logger.info(station+"Entered into Open");

        // store connecting user information
        sessionMap.put(session, station);
        stattionMap.put(station, session);

        //Send chat history to the newly connected user
        //sendMessageToPArticularUser(username, getChatHistory());
        //stattionMap.get(station).getBasicRemote().sendText(dealRepo.findByStationName(station).getContent());
        String msg="";
        if (dealRepo.findTopByStationNameOrderByIdDesc(station) != null)
            msg = dealRepo.findTopByStationNameOrderByIdDesc(station).getContent();
        if (! msg.equals(""))
            if (stattionMap.get(station).getBasicRemote() != null)
                stattionMap.get(station).getBasicRemote().sendText(msg);
        // broadcast that new user joined
//        String message = "User:" + username + " has Joined the Chat";
//        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, String deal) throws IOException {
        logger.info("Entered into Message: Got deal:" + deal);
        String stationName = sessionMap.get(session);

        // Direct message to a user using the format "@username <message>"

        // Broadcast the message
       // broadcast("Todays deal from, "+stationName + ": " + deal);
        stattionMap.get(stationName).getBasicRemote().sendText("Todays deal from, "+stationName + ": " + deal);

        // Saving chat history to repository
        dealRepo.save(new Deal(stationName, deal));

    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        String username = sessionMap.get(session);
        sessionMap.remove(session);
        stattionMap.remove(username);

        String message = username + " disconnected";
        //broadcast(message);
    }



    private void broadcast(String message) {
        sessionMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }

}
