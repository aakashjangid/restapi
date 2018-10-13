package com.iotapi.restapp.daoimpl;

import java.sql.Connection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iotapi.restapp.model.Customer;



@Repository
public class CustomerDAOImpl {

	Connection con;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean registerCustomer(Customer customer) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Integer id = (Integer) session.save(customer);
		commitAndCloseSession(session);
		if(id!=null) return true;
		return false;
		
	}

	public boolean loginCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "FROM Customer WHERE email=:email and password=:password";
		Query query = session.createQuery(sql);
		query.setString("email", customer.getEmail());
		query.setString("password", customer.getPassword());
		Customer customertemp = (Customer) query.uniqueResult();
		commitAndCloseSession(session);
		if(customertemp!=null)return true;
		return false;
	}
	
		
	private void commitAndCloseSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}
	
}
