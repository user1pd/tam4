package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Employee extends Person implements Serializable{
	@OneToMany (mappedBy="teamLeader", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=false)
	private List<Project> projects = new ArrayList<>();
	
//------------------------------------------------------------------------------------------------
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
	public static String BASE_URL = "http://localhost:8080/MSD-S2/data/employees/";
	@XmlElement(name="link") 
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getIdPerson();
		return new AtomLink(restUrl, "get-employee");
	}
	
	public void setLink(AtomLink link) {
		
	}
	
	//-------------CONSTRUCTOR---------------------------------

	public Employee() {
		super();
	}

	public Employee(Integer idPerson, String name, String email, String telNumber, Date birthdate, String username,
			String password) {
		super(idPerson, name, email, telNumber, birthdate, username, password);
	}


	
	

	


	
	
	
	
}
