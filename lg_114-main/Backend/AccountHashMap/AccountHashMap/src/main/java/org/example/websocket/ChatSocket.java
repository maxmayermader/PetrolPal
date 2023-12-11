package org.example.websocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ServerEndpoint(value = "/chat/{username}", configurator = ChatSocketConfig.class)
public class ChatSocket {

	private static MessageRepository msgRepo;
	private String s;
	@Autowired
	public void setMessageRepository(MessageRepository repo) {
		msgRepo = repo;
	}

	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(ChatSocket.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		logger.info("Entered into Open");

		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);

		sendMessageToPArticularUser(username, getChatHistory(username));

		String message = "User:" + username + " has Joined the Chat";
		broadcast(message);
	}


	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		logger.info("Entered into Message: Got Message:" + message);
		String username = sessionUsernameMap.get(session);

		// Direct message to a user using the format "@username <message>"
		if (message.startsWith("@")) {
			String[] messageParts = message.split(" ", 2); // Split the message into two parts: recipient and message
			String recipientUsername = messageParts[0].substring(1); // Extract recipient's username
			String messageContent = messageParts[1]; //  	 	Extract the message content

			// Ensure the recipient's session exists
			Session recipientSession = usernameSessionMap.get(recipientUsername);
			s = recipientUsername;
			if (recipientSession != null) {//if recipient is connected send message to both user and recepient
				sendMessageToPArticularUser(recipientUsername, "[TO] " + username + ": " + messageContent);
				sendMessageToPArticularUser(username, "[FROM] " + username + ": " + messageContent);

			}
			else{ //if not connected send message to just user, stores message
				sendMessageToPArticularUser(username, "[FROM] " + username + ": " + messageContent);

			}
		} else {
			// Broadcast the message
			broadcast(username + ": " + message);
		}

		// Saving chat history to repository
		msgRepo.save(new Message(username, message));
	}


	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

		String message = username + " disconnected";
		broadcast(message);
	}


	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}


	private void sendMessageToPArticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		}
    catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}


	private void broadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			}
      catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});

	}

	private String getChatHistory(String username) {
		List<Message> messages = msgRepo.findAll();

		// Convert the list to a string
		StringBuilder sb = new StringBuilder();

		if (messages != null && messages.size() != 0) {
			for (Message message : messages) {
				// Check if the message is not a private message or if it's a private message directed to the new user
				if (!isPrivateMessage(message) || isMessageForUser(message, username) || message.getUserName().equals(username)) {
					sb.append(message.getUserName() + ": " + message.getContent() + "\n");
				}
			}
		}

		return sb.toString();
	}

	private boolean isPrivateMessage(Message message) {
		// Check if the message content starts with "@" followed by a username
		String content = message.getContent();
		return content.startsWith("@") && content.contains(" ");
	}

	// Helper method to check if a private message is directed to a specific user
	private boolean isMessageForUser(Message message, String username) {
		String content = message.getContent();
		return content.contains("@" + username);
	}







}
