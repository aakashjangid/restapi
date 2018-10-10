package com.iotapi.restapp.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iotapi.restapp.dao.BaseEntityDao;
import com.iotapi.restapp.model.BaseEntity;

@Repository
public class BaseEntityDaoImpl implements BaseEntityDao {

	
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public void insertData(BaseEntity entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(entity);
		session.getTransaction().commit();
		session.close();
	}

	
}
