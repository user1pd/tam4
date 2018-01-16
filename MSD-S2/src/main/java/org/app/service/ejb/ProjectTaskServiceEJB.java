package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Stateless @LocalBean
public class ProjectTaskServiceEJB 	
		extends EntityRepositoryBase<Project>
		implements ProjectTaskService{
	private static Logger logger = Logger.getLogger(ProjectTaskServiceEJB.class.getName());
	
	@EJB 
	private EmployeeService employeeService;
	private EntityRepository<Task> taskRepository;	
	
	@PostConstruct
	public void init() {		
		taskRepository=new EntityRepositoryBase<Task>(this.em, Task.class);
		logger.info("POSTCONSTRUCT-INIT taskService: " + this.taskRepository);
		logger.info("POSTCONSTRUCT-INIT employeeDataService: " + this.employeeService);		
	}
	
	// Aggregate factory method
	public Project createNewProject(Integer id){
		Project project = new Project(id, "Pr." + id , new Date());
		
		List<Task> tasksProject = new ArrayList<>();
		Integer taskCount = 3;
		for (int i=1; i<=taskCount; i++){
			tasksProject.add(new Task(null, "T: " + project.getIdProject() + "." + i, project));		
		}		
		
		project.setTasks(tasksProject);
		
		//set team leader
		project.setTeamLeader(employeeService.getEmployees().get(0));
		employeeService.getEmployees().get(0).getProjects().add(project);
	
		// save project aggregate
		this.add(project);
		// return project aggregate to service client
		return project;
	}

	
	public Task getTaskById(Integer taskid) {
		return taskRepository.getById(taskid);
	}

	public String getMessage() {
		return "ProjectTask DataService is working...";
	}
}



