package com.iotapi.restapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * this is the user POJO. this will be used as a data traveler between the
 * different layers of the application this will also work as an entity for
 * database(users)
 * 
 * Date - 04/04/2018
 * 
 * @author ishan.juneja
 * @Entity Every persistent POJO class is an entity and is declared using
 *         the @Entity annotation
 * @Table annotation allows you to specify the details of the table that will be
 *        used to persist the entity in the database.
 */
@Entity
@Table(name = "users")
public class User {

	/**
	 * this will be the id of the current user
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
	 * this will be the first name of the current user
	 * 
	 * @Column The column(s) used for a property mapping can be defined using
	 *         the @Column annotation
	 */
	@Column(name = "name")
	private String firstname;

	/**
	 * this will be the last name of the current user
	 * 
	 * @Column The column(s) used for a property mapping can be defined using
	 *         the @Column annotation
	 */
	@Column(name = "lastname")
	private String lastname;

	/**
	 * this will be the login name for the current user
	 * 
	 * @Column The column(s) used for a property mapping can be defined using
	 *         the @Column annotation
	 */
	@Column(name = "loginname")
	private String loginname;

	/**
	 * this will be the password for the current user
	 * 
	 * @Column The column(s) used for a property mapping can be defined using
	 *         the @Column annotation
	 */
	@Column(name = "password")
	private String password;

	/**
	 * a default constructor for User class.
	 */
	public User() {

	}

	/**
	 * a parameterized constructor required for the conversion to JSON
	 */
	public User(int id, String firstname, String lastname, String loginname, String password) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.loginname = loginname;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
