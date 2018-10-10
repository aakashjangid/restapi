package com.iotapi.restapp.runners;

import org.springframework.beans.factory.annotation.Autowired;

import com.iotapi.restapp.dao.BaseEntityDao;
import com.iotapi.restapp.model.BaseEntity;

public class DatabaseInsertion implements Runnable {

	@Autowired
	BaseEntityDao baseDao;
	
	private BaseEntity entity;
	
	public void setEntity(BaseEntity entity) {
	this.entity=entity;	
	}
	
	@Override
	public void run() {
		
		baseDao.insertData(entity);

	}

}
