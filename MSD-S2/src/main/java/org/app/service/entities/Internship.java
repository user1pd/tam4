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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.service.ejb.InternshipService;
@XmlRootElement(name="internship")
@XmlAccessorType(XmlAccessType.NONE) 
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
	@XmlElement()
	public Date getInternshipStartDate() {
		return internshipStartDate;
	}
	public void setInternshipStartDate(Date internshipStartDate) {
		this.internshipStartDate = internshipStartDate;
	}
	@XmlElement()
	public Date getInternshipEndDate() {
		return internshipEndDate;
	}
	public void setInternshipEndDate(Date internshipEndDate) {
		this.internshipEndDate = internshipEndDate;
	}
	@XmlElement()
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
	@XmlElement()
	public Integer getIdInternship() {
		return idInternship;
	}
	public void setIdInternship(Integer idInternship) {
		this.idInternship = idInternship;
	}
//	@XmlElementWrapper(name="tasks") @XmlElement(name="task")
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	//------------------------------------------------------------------------------------------------
	public Internship() {
		super();
	}
	public Internship(Integer id, Date start, Date end, Aplicant aplicant) {
		super();
		this.idInternship = id;
		this.internshipStartDate = start;
		this.internshipEndDate = end;
		this.aplicant = aplicant;
	}
	public Internship(Integer id, Date start, Date end) {
		super();
		this.idInternship = id;
		this.internshipStartDate = start;
		this.internshipEndDate = end;
	}

	public  Internship(Integer id, Date start, Date end, Integer daysSch) {
		
		Internship internship=new Internship(id, start, end);
		List<Schedule> scheduleI=new ArrayList<>();
		
		for (int i=1; i<=daysSch-1;i++) {
			scheduleI.add(new Schedule(null, i, "10:00", "16:00", internship));
		}
		internship.setSchedule(scheduleI);
	}
	
	
	

	
	

	
	
	
}
