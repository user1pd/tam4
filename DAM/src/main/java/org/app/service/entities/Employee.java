package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Employee extends Person{
	@OneToMany (mappedBy="teamLeader", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=false)
	private List<Project> projects = new ArrayList<>();
	
//------------------------------------------------------------------------------------------------
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
//------------------------------------------------------------------------------------------------
	public Employee(Integer idPerson, String name, String email, String telNumber, Date birthdate, String username,
			String password, List<Project> projects) {
		super(idPerson, name, email, telNumber, birthdate, username, password);
		this.projects = projects;
	}

public Employee() {
	super();
}
	


	
	
	
	
}
