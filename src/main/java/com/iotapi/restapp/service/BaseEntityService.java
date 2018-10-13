package com.iotapi.restapp.service;

import java.util.List;

import com.iotapi.restapp.model.BaseEntity;

public interface BaseEntityService {

	public void sendMessage(BaseEntity entity);
	
	public void insertData(BaseEntity entity);

	public List<BaseEntity> getAllValues();
}
