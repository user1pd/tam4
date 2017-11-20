package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project {
	@Id	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idProject;
	
	@Temporal(TemporalType.DATE) 
	private Date startDate;
	@Temporal(TemporalType.DATE) 
	private Date endDate;
	private String description;
	
	@ManyToOne
	private Employee teamLeader;
	@OneToMany (mappedBy="project", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=false)
	private List<Task> tasks = new ArrayList<>();
	
//---------------get & set------------------------
	public Integer getIdProject() {
		return idProject;
	}
	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Employee getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(Employee teamLeader) {
		this.teamLeader = teamLeader;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}


	
	
	
}
