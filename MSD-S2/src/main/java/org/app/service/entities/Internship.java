package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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

import org.app.service.ejb.InternshipService;
@Entity
public class Internship implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idInternship;
	
	@Temporal(TemporalType.DATE) 
	private Date internshipStartDate;
	@Temporal(TemporalType.DATE) 
	private Date internshipEndDate;
	
	@ManyToOne
	private Aplicant aplicant;
	@OneToMany (mappedBy="internship", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=false)
	private List<Schedule> schedule = new ArrayList<>();
	@OneToMany (mappedBy="internship", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=false)
	private List<Task> tasks = new ArrayList<>();
	
//---------------get & set-----------------------------------------------------------------------
	public Date getInternshipStartDate() {
		return internshipStartDate;
	}
	public void setInternshipStartDate(Date internshipStartDate) {
		this.internshipStartDate = internshipStartDate;
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
	public Integer getIdInternship() {
		return idInternship;
	}
	public void setIdInternship(Integer idInternship) {
		this.idInternship = idInternship;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	//------------------------------------------------------------------------------------------------
	public Internship(Integer idInternship, Date internshipStartDate, Date internshipEndDate, Aplicant aplicant,
			List<Schedule> schedule, List<Task> tasks) {
		super();
		this.idInternship = idInternship;
		this.internshipStartDate = internshipStartDate;
		this.internshipEndDate = internshipEndDate;
		this.aplicant = aplicant;
		this.schedule = schedule;
		this.tasks = tasks;
	}
	public Internship() {
		super();
	}
	public Internship(Integer idInternship, Date internshipStartDate, Date internshipEndDate, Aplicant aplicant) {
		super();
		this.idInternship = idInternship;
		this.internshipStartDate = internshipStartDate;
		this.internshipEndDate = internshipEndDate;
		this.aplicant = aplicant;
	}
	public Internship(Integer idInternship, Date internshipStartDate, Date internshipEndDate) {
		super();
		this.idInternship = idInternship;
		this.internshipStartDate = internshipStartDate;
		this.internshipEndDate = internshipEndDate;
	}
	//------------------------------------------------------------------------------------------------
	public Internship buildInternship(Integer id, Date start, Date end, Integer daysNr) {
		
		Internship internship=new Internship(id, start, end);
		List<Schedule> scheduleI=new ArrayList<>();
		
		for (int i=0; i<=daysNr;i++) {
			scheduleI.add(new Schedule(null, i, "10:00", "16:00", internship));
		}
		internship.setSchedule(scheduleI);
		return internship;
	}
	

	
	

	
	
	
}
