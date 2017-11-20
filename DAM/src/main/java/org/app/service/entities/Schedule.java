package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity 
public class Schedule {
	@Id
	private Integer id;
	private Integer day;
	private String startHour;
	private String endHour;
	@ManyToOne 
	private Internship internship;
	
//---------------get & set------------------------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public Internship getInternship() {
		return internship;
	}
	public void setInternship(Internship internship) {
		this.internship = internship;
	}
	//------------------------------------------------------------------------------------------------
	public Schedule(Integer id, Integer day, String startHour, String endHour, Internship internship) {
		super();
		this.id = id;
		this.day = day;
		this.startHour = startHour;
		this.endHour = endHour;
		this.internship = internship;
	}
	public Schedule() {
		super();
	}
	


	
}
