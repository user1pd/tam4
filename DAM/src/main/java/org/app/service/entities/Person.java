package org.app.service.entities;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass 
public abstract class Person {
	
	@Id	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idPerson;
	private String name;
	private String email;
	private String telNumber;
	@Temporal(TemporalType.DATE) 
	private Date birthdate;
	private String username;
	private String password;
	
//---------------get & set------------------------
	public Integer getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//------------------------------------------------------------------------------------------------
	public Person(Integer idPerson, String name, String email, String telNumber, Date birthdate, String username,
			String password) {
		super();
		this.idPerson = idPerson;
		this.name = name;
		this.email = email;
		this.telNumber = telNumber;
		this.birthdate = birthdate;
		this.username = username;
		this.password = password;
	}
	public Person() {
		super();
	}
	
	

	

}
