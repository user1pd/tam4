package org.app.service.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Stateless @LocalBean
public class ProjectTaskServiceEJB extends EntityRepositoryBase<Project>
		implements ProjectTaskService, Serializable {
	private static Logger logger = Logger.getLogger(ProjectTaskServiceEJB.class.getName());
	
	@EJB // injected DataService
	private TaskService featureDataService; 
	// Local component-entity-repository
	private EntityRepository<Task> taskRepository;
	
	@PostConstruct
	public void init() {
		// local instantiation of local component-entity-repository
		taskRepository = new EntityRepositoryBase<Task>(this.em,Task.class);
		logger.info("POSTCONSTRUCT-INIT taskRepository: " + this.taskRepository);
		logger.info("POSTCONSTRUCT-INIT featureDataService: " + this.featureDataService);
	}
	
	// Aggregate factory method
	public Project createNewProject(Integer id){
		// create project aggregate
		Project project = new Project(id, "Project" + id , new Date());
		List<Task> tasksProject = new ArrayList<>();
		Date startDate = new Date();
		Long interval =  30l /*zile*/ * 24 /*ore*/ * 60 /*min*/ * 60 /*sec*/ * 1000 /*milisec*/;
		Integer taskCount = 3;
		for (int i=0; i<=taskCount-1; i++){
			tasksProject.add(new Task(null, "T: " + project.getIdProject() + "." + i, project));
			
		}
		project.setTasks(tasksProject);		
		// save project aggregate
		this.add(project);
		// return project aggregate to service client
		return project;
	}
	
	public Task getTaskById(Integer taskid) {
		return taskRepository.getById(taskid);
	}

	public String getMessage() {
		return "ProjectSprint DataService is working...";
	}
}



