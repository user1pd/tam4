package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Aplicant extends Person {
	private String university;
	private String department;
	
	@OneToMany (mappedBy="aplicant", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=false)
	private List<Internship> internships = new ArrayList<>();
	
//---------------get & set------------------------
	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

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

	public Aplicant() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Aplicant(Integer idPerson, String name, String email, String telNumber, Date birthdate, String username,
			String password, String university, String department, List<Internship> internships) {
		super(idPerson, name, email, telNumber, birthdate, username, password);
		this.university = university;
		this.department = department;
		this.internships = internships;
	}




	
//--------------------------------------------------------------------
	

	
	
	
}
