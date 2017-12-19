package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Remote
public interface TaskService  {

	// create or update
   Task addTask(Task TaskToAdd);	   
   String removeTask(Task TaskToDelete);
	   
    Task getTaskByTaskId(Integer TaskId);
    Collection<Task> getTasks();
    
    String getMessage();
	Project getProjectByProjectId(Integer idProject);
	Collection<Task> toCollection();
	Task getById(Integer id);

}
