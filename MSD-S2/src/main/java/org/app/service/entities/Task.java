package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="task")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Task implements Serializable{
	@Id	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idTask;
	
	private String name;
	private String evolution;
	private Integer evaluation;
	
	@ManyToOne
	private Project project;
	@ManyToOne 
	private Internship internship;
	
//....................REST Resource URL
	public static String BASE_URL = Project.BASE_URL;
	@XmlElement(name="link") 
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL
				+this.getProject().getIdProject()
				+"/tasks/"
				+ this.getIdTask();
		return new AtomLink(restUrl, "get-task");
	}
	
	public void setLink(AtomLink link) {
		
	}
	
//---------------get & set------------------------

	@XmlElement
	public Integer getIdTask() {
		return idTask;
	}
	public void setIdTask(Integer idTask) {
		this.idTask = idTask;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement()
	public String getEvolution() {
		return evolution;
	}
	public void setEvolution(String evolution) {
		this.evolution = evolution;
	}
	@XmlElement()
	public Integer getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}	
	@XmlElement()
	public Internship getInternship() {
		return internship;
	}
	public void setInternship(Internship internship) {
		this.internship = internship;
	}
	
	@XmlElement
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	//-----------CONSTRUCTORS--------------------------

	public Task() {
		super();
	}
	public Task(Integer idTask, String name, Project project) {
		super();
		this.idTask = idTask;
		this.name = name;
		this.project = project;
	}

	
	
	

	
	
}
