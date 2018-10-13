package com.iotapi.restapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iotapi.restapp.MainRunner;
import com.iotapi.restapp.model.BaseEntity;
import com.iotapi.restapp.service.BaseEntityService;



@RestController
public class RestControllerApi {

	@Autowired
	BaseEntityService entityService;
	
	@RequestMapping(path="/inputs/{voltage}/{current}",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String insertValue(@PathVariable("voltage") String voltagein,@PathVariable("current") String currentin) {
		
		Double voltage= Double.parseDouble(voltagein);
		Double current = Double.parseDouble(currentin);
		BaseEntity entity=new BaseEntity();
		entity.setCurrent(current);
		entity.setVoltage(voltage);
		/*BaseEntity entity = null;
		try {
			entity = new ObjectMapper().readValue(body, BaseEntity.class);
		}  catch (Exception e) {
			e.printStackTrace();
		}*/
		
		System.out.println("voltage is "+entity.getVoltage());
		System.out.println("current is "+entity.getCurrent());
		entityService.sendMessage(entity);
		
		
		return "{running}";
	}
	
	@RequestMapping(path="/sampling")
	public void testGenerateValues() throws InterruptedException {
		for (int i = 0; i < 20; i++) {
			new MainRunner().sendPostRequest("http://localhost:8080/inputs/"+i*Math.random()+"/"+i*Math.random());
			Thread.sleep(1000);
		}
	}
	
	@RequestMapping(path="/getCurrentAndVoltage")
	public List<BaseEntity> getCurrentAndVoltage() throws InterruptedException {
		
		return entityService.getAllValues();
		
	}
	
	/*@RequestMapping(path="/instantsampling")
	public void testGenerateValuesInstant() throws InterruptedException {
		for (int i = 0; i < 2000; i++) {
			new MainRunner().sendPostRequest("http://localhost:8080/inputs", "{\"voltage\":\""+i*Math.random()+"\",\"current\":\""+i*Math.random()+"\"}");
		}
	}*/
	
}
