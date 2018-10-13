package com.iotapi.restapp.dao;

import java.util.List;

import com.iotapi.restapp.model.BaseEntity;

public interface BaseEntityDao {

	public void insertData(BaseEntity entity);

	public List<BaseEntity> getAllValues();
}
