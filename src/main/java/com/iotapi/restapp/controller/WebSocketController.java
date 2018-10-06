package com.iotapi.restapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

public class WebSocketController {

	
	private final SimpMessagingTemplate template;
	
	@Autowired
	public WebSocketController(SimpMessagingTemplate template) {
		this.template=template;
	}
	
	@MessageMapping
	public void onRecieveMessage(String message) {
		
		this.template.convertAndSend("/value", new SimpleDateFormat("HH:mm:ss")
				.format(new Date())+message);
	}
}
