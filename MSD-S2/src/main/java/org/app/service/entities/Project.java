
package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@XmlRootElement(name="project")
@XmlAccessorType(XmlAccessType.NONE) 
@Entity
public class Project implements Serializable{
	@Id	
	private Integer idProject;
	private String name;
	
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
	@XmlElement()
	public Integer getIdProject() {
		return idProject;
	}
	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}
	@XmlElement()
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@XmlElement()
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@XmlElement()
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement()
	public Employee getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(Employee teamLeader) {
		this.teamLeader = teamLeader;
	}
	@XmlElementWrapper(name="tasks") @XmlElement(name="task")
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	@XmlElement()
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//---------------------------------------------------------------------
	
	@Override
	public String toString() {
		return "Project [IdProject=" + idProject + ", name=" + name + "]";
	}
	
	//-----------CONSTRUCTORS-------------------------
	
	public Project() {
		super();
	}
	
public Project(Integer id) {
		
		this.idProject = id;
}
	
	public Project(Integer id, String name, Date start) {
		
		this.idProject = id;
		this.name = name;
		this.startDate = start;
	}
	
	
	public Project(Integer idProject, String name, Date startDate, Integer tasksCounter) {
		super();
		this.idProject = idProject;
		this.name = name;
		this.startDate = startDate;
		List<Task> tasksP=new ArrayList<>();
		
		for(int i=1; i<=tasksCounter-1; i++) {
			tasksP.add(new Task(null, "T: " + this.getIdProject() + "." + i, this));			
		}
		this.setTasks(tasksP);
	}
	
	//--------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProject == null) ? 0 : idProject.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (idProject == null) {
			if (other.idProject != null)
				return false;
		} else if (!idProject.equals(other.idProject))
			return false;
		return true;
	}
	
	
	
	/* Rest Resource URL*/
	public static String BASE_URL = "http://localhost:8080/MSD-S2/data/projects/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ this.getIdProject();
        return new AtomLink(restUrl, "get-project");
    }	
	
	public void setLink(AtomLink link){}
	
	
	
	



	
	
	
}
