package com.iotapi.restapp.service;

import com.iotapi.restapp.model.BaseEntity;

public interface BaseEntityService {

	public void sendMessage(BaseEntity entity);
	
	public void insertData(BaseEntity entity);
}
