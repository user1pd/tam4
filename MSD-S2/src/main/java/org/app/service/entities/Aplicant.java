package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Aplicant extends Person implements Serializable {
	private String university;
	private String department;
	
	@OneToMany (mappedBy="aplicant", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=false)
	private List<Internship> internships = new ArrayList<>();
	
	public static String BASE_URL = "http://localhost:8080/MSD-S2/data/employees/";
	@XmlElement(name="link") 
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getIdPerson();
		return new AtomLink(restUrl, "get-employee");
	}
	
	public void setLink(AtomLink link) {
		
	}
	
	
//---------------get & set------------------------
	@XmlElement
	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
    
	@XmlElement
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Internship> getInternships() {
		return internships;
	}

	public void setInternships(List<Internship> internships) {
		this.internships = internships;
	}
	
	//-------------CONSTRUCTOR---------------------------------
	
	public Aplicant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Aplicant(Integer idPerson, String name, String email, String telNumber, Date birthdate, String username,
			String password, String department, String university) {
		super(idPerson, name, email, telNumber, birthdate, username, password);
		this.department = department;
		this.university = university;
	}








	

	

	
	
	
}
