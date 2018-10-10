package com.iotapi.restapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotapi.restapp.MainRunner;
import com.iotapi.restapp.model.BaseEntity;
import com.iotapi.restapp.service.BaseEntityService;
import com.iotapi.restapp.service.MyMessageHandler;



@RestController
public class RestControllerApi {

	@Autowired
	BaseEntityService entityService;
	
	@RequestMapping(path="/inputs",method=RequestMethod.POST,consumes=MediaType.TEXT_PLAIN_VALUE)
	public String insertValue(@RequestBody String body) {
		BaseEntity entity = null;
		try {
			entity = new ObjectMapper().readValue(body, BaseEntity.class);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		entityService.sendMessage(entity);
		
		
		return null;
	}
	
	@RequestMapping(path="/sampling")
	public void testGenerateValues() throws InterruptedException {
		for (int i = 0; i < 200; i++) {
			new MainRunner().sendPostRequest("http://localhost:8080/inputs", "{\"voltage\":\""+i*Math.random()+"\",\"current\":\""+i*Math.random()+"\"}");
			Thread.sleep(1000);
		}
	}
	
	@RequestMapping(path="/instantsampling")
	public void testGenerateValuesInstant() throws InterruptedException {
		for (int i = 0; i < 2000; i++) {
			new MainRunner().sendPostRequest("http://localhost:8080/inputs", "{\"voltage\":\""+i*Math.random()+"\",\"current\":\""+i*Math.random()+"\"}");
		}
	}
	
}
