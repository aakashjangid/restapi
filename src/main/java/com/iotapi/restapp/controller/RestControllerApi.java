package com.iotapi.restapp.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotapi.restapp.model.BaseEntity;
import com.iotapi.restapp.service.MyMessageHandler;



@RestController
public class RestControllerApi {

	
	@RequestMapping(path="/inputs",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public String insertValue(@RequestBody String voltage) {
		BaseEntity entity = null;
		try {
			entity = new ObjectMapper().readValue(voltage, BaseEntity.class);
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
		try {
			MyMessageHandler.session.sendMessage(new TextMessage(entity.getVoltage().toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
