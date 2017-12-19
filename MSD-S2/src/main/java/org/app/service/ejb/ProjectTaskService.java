package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Remote
public interface ProjectTaskService extends EntityRepository<Project>{
	
	// create aggregate entity: project root with releases as components
	Project createNewProject(Integer id);
	// Query method on release components
	Task getTaskById(Integer taskid);
	// Other
	String getMessage();
}
