package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	@Id	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idTask;
	
	@ManyToOne 
	private Internship internship;
	@ManyToOne
	private Project project;
	
	private String task;
	private String evolution;
	private Integer evaluation;

	
//---------------get & set------------------------

	public String getTask() {
		return task;
	}
	public Integer getIdTask() {
		return idTask;
	}
	public void setIdTask(Integer idTask) {
		this.idTask = idTask;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getEvolution() {
		return evolution;
	}
	public void setEvolution(String evolution) {
		this.evolution = evolution;
	}
	public Integer getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}	
	public Internship getInternship() {
		return internship;
	}
	public void setInternship(Internship internship) {
		this.internship = internship;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	//------------------------------------------------------------------------------------------------
	public Task(Integer idTask, Internship internship, Project project, String task, String evolution,
			Integer evaluation) {
		super();
		this.idTask = idTask;
		this.internship = internship;
		this.project = project;
		this.task = task;
		this.evolution = evolution;
		this.evaluation = evaluation;
	}
	public Task() {
		super();
	}
	

	
	
}
