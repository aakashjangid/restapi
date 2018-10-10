package com.iotapi.restapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class MyMessageHandler extends TextWebSocketHandler{

	public static WebSocketSession session;
	
	public static List<WebSocketSession> voltageSessions = new ArrayList<>();
	public static List<WebSocketSession> currentSessions = new ArrayList<>();
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("===conection closed===");
		if(session.getUri().toString().contains("voltage")) {
			MyMessageHandler.voltageSessions.remove(session);
			}
			if(session.getUri().toString().contains("current")) {
				MyMessageHandler.currentSessions.remove(session);
				}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connection established");
		if(session.getUri().toString().contains("voltage")) {
		MyMessageHandler.voltageSessions.add(session);
		}
		if(session.getUri().toString().contains("current")) {
			MyMessageHandler.currentSessions.add(session);
			}
		super.afterConnectionEstablished(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("message recieved "+message.getPayload());
		super.handleTextMessage(session, message);
	}
}
