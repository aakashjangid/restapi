package com.iotapi.restapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "customers")
public class Customer {
	/**
	 * this will be the id of the current customer
	 * 
	 * @Id declares the identifier property of this entity
	 * @GeneratedValue can be used to define the identifier generation strategy
	 * @Column The column(s) used for a property mapping can be defined using
	 *         the @Column annotation
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;


	/**
	 * this will be the firstname of the customer
	 */
	@Column(name = "firstName")
	private String firstName;

	/**
	 * this will be the lastname of the customer
	 */
	@Column(name = "lastName")
	private String lastName;

	/**
	 * this will be the email of the current customer
	 * 
	 * @Column The column(s) used for a property mapping can be defined using
	 *         the @Column annotation
	 */
	@Column(name = "email")
	private String email;

	/**
	 * this will be the contact number of the user
	 */
	@Column(name = "contact")
	private String contact;

	
	/**
	 * this will be the password of the current customer which will not be saved
	 * in to database
	 * 
	 * @Transient This is used when you don’t want to persist the value in
	 *            database.
	 */
	@Column(name="password")
	private String password;

	
	
	/**
	 * this is a default constructor
	 */
	public Customer() {
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Customer(int id, String firstName, String lastName, String email, String contact, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contact = contact;
		this.password = password;
	}

	
}
