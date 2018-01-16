package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Remote
public interface ProjectTaskService extends EntityRepository<Project>{
	
	// create aggregate entity
	Project createNewProject(Integer id);	
	
	//read	
	Task getTaskById(Integer taskId);
	
	// Other
	String getMessage();
	
//	Project getProjectById(Integer id);
}
