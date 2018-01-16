package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Singleton
public class ProjectFactory {
	@TransactionAttribute(TransactionAttributeType.SUPPORTS) // propagate transaction
	public static Project buildProiect(Integer projectID, String name, Integer taskCount){
		
		Project project = new Project(projectID, name + "." + projectID , new Date());
		
		List<Task> tasksProject = new ArrayList<>();		
		for (int i=0; i<=taskCount-1; i++){
			tasksProject.add(new Task(null, "T: " + project.getIdProject() + "." + i, project));
		}		
		
		project.setTasks(tasksProject);
		
		return project;
	}

	@Override
	public String toString() {
		return "ProjectFactory-instance";
	}

}
