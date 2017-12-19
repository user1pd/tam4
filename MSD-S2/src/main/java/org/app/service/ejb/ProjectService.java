package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Project;

@Remote
public interface ProjectService {
	// create or update
	   Project addProject(Project ProjectToAdd);	   
	   String removeProject(Project ProjectToDelete);
		   
	    Project getProjectByProjectId(Integer ProjectId);
	    Collection<Project> getProjects();
	    
	    String getMessage();

}
