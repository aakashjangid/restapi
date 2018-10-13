package com.iotapi.restapp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iotapi.restapp.model.Customer;
import com.iotapi.restapp.serviceimpl.CustomerServiceImpl;

@Controller
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerService;

	@RequestMapping(value = "/customer/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public boolean authenticateUser(@RequestBody Customer customer, HttpServletResponse httpServletResponse) {

		if (customerService.loginCustomer(customer).equalsIgnoreCase("LOGGED IN")) {
			return true;
		} else {
			throw new RuntimeException("incorrect credentials");

		}
	}

	@RequestMapping(value = "/customer/register", method = RequestMethod.POST,produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String customerRegisteration(@RequestBody Customer customer) {
		return customerService.registerCustomer(customer);
	}
}
