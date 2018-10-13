package com.iotapi.restapp.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.iotapi.restapp.daoimpl.CustomerDAOImpl;
import com.iotapi.restapp.model.Customer;

@Service
public class CustomerServiceImpl {

	@Autowired
	CustomerDAOImpl customerDAO;
	
	
	public String registerCustomer(Customer customer) {
		String login="email exists already";
		
		customerDAO.registerCustomer(customer);
		
		
		return login;
	}

	public String loginCustomer(Customer customer) {
		if(customerDAO.loginCustomer(customer)) return "LOGGED IN";
		return "UNSUCCESSFULL";
	}

	
}
