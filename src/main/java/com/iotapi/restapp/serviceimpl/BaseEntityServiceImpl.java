package com.iotapi.restapp.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.iotapi.restapp.dao.BaseEntityDao;
import com.iotapi.restapp.model.BaseEntity;
import com.iotapi.restapp.service.BaseEntityService;
import com.iotapi.restapp.service.MyMessageHandler;

@Service
public class BaseEntityServiceImpl implements BaseEntityService {

	@Autowired
	BaseEntityDao entityDao;
	
	@Override
	public void sendMessage(BaseEntity entity) {

		try {

			for (WebSocketSession session : MyMessageHandler.voltageSessions) {
				session.sendMessage(new TextMessage(entity.getVoltage().toString()));
			}
			for (WebSocketSession session : MyMessageHandler.currentSessions) {
				session.sendMessage(new TextMessage(entity.getCurrent().toString()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		insertData(entity);
	}

	@Override
	public void insertData(BaseEntity entity) {
		
		Executors.newCachedThreadPool().execute(new Runnable() {
		    @Override
		    public void run() {
		       entityDao.insertData(entity);
		    }
		});
		
	}

	@Override
	public List<BaseEntity> getAllValues() {
		return entityDao.getAllValues();
		
	}

}
