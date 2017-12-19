package org.app.service.entities;

import java.io.Serializable;
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
public class Internship implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idIntership;
	
	@Temporal(TemporalType.DATE) 
	private Date intershipStartDate;
	@Temporal(TemporalType.DATE) 
	private Date internshipEndDate;
	
	@ManyToOne
	private Aplicant aplicant;
	@OneToMany (mappedBy="internship", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=false)
	private List<Schedule> schedule = new ArrayList<>();
	@OneToMany (mappedBy="internship", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=false)
	private List<Task> tasks = new ArrayList<>();
	
//---------------get & set-----------------------------------------------------------------------
	public Date getIntershipStartDate() {
		return intershipStartDate;
	}
	public void setIntershipStartDate(Date intershipStartDate) {
		this.intershipStartDate = intershipStartDate;
	}
	public Date getInternshipEndDate() {
		return internshipEndDate;
	}
	public void setInternshipEndDate(Date internshipEndDate) {
		this.internshipEndDate = internshipEndDate;
	}
	public Aplicant getAplicant() {
		return aplicant;
	}
	public void setAplicant(Aplicant aplicant) {
		this.aplicant = aplicant;
	}
	public List<Schedule> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}
	public Integer getIdIntership() {
		return idIntership;
	}
	public void setIdIntership(Integer idIntership) {
		this.idIntership = idIntership;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	//------------------------------------------------------------------------------------------------
	public Internship(Integer idIntership, Date intershipStartDate, Date internshipEndDate, Aplicant aplicant,
			List<Schedule> schedule, List<Task> tasks) {
		super();
		this.idIntership = idIntership;
		this.intershipStartDate = intershipStartDate;
		this.internshipEndDate = internshipEndDate;
		this.aplicant = aplicant;
		this.schedule = schedule;
		this.tasks = tasks;
	}
	public Internship() {
		super();
	}
	public Internship(Integer idIntership, Date intershipStartDate, Date internshipEndDate, Aplicant aplicant) {
		super();
		this.idIntership = idIntership;
		this.intershipStartDate = intershipStartDate;
		this.internshipEndDate = internshipEndDate;
		this.aplicant = aplicant;
	}
	
	

	
	
	
}
