package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Remote
public interface TaskService  {

	// create or update
   Task addTask(Task TaskToAdd);	 
   
   //delete
   String removeTask(Task TaskToDelete);
   
	//read   
    Task getTaskById(Integer TaskId);
    List<Task> getTasks();
    
    //others
    String getMessage();
    
    
	Project getProjectById(Integer idProject);
//	Collection<Task> toCollection();
//	Task getById(Integer id);

}
