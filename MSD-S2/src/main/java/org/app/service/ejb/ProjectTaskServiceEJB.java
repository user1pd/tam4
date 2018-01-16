package org.app.service.ejb;

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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Project;
import org.app.service.entities.Task;
@Path("projectst") 
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
	
	//.......................... Aggregate factory method	
	@POST @Path("/new/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 	
	public Project createNewProject(@PathParam("id")Integer id){
		logger.info("**** DEBUG REST createNewProject POST");
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
	//............................................READ
	@GET @Path("/{projectid}/tasks/{taskid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Task getTaskById(@PathParam("taskid")Integer taskid) {
		logger.info("DEBUG: called getTaskById() for projects >>>>>>>>>>>>>>");
		return taskRepository.getById(taskid);
	}

	@GET @Path("/test") 
	@Produces({ MediaType.TEXT_PLAIN})
	public String getMessage() {
		return "ProjectTask DataService is working...";
	}
}



