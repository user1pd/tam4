package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Remote
public interface ProjectService {
	// create or update
	Project addProject(Project ProjectToAdd);	   
	
	//delete
	String removeProject(Project ProjectToDelete);
	
	//read	
	Project getProjectById(Integer ProjectId);
	List<Project> getProjects();
	
	//others
	String getMessage();
	
	Task getTaskById(Integer TaskId);

}
