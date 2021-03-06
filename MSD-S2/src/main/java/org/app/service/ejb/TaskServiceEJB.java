package org.app.service.ejb;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Project;
import org.app.service.entities.Task;

@Path("tasks")
@Stateless @LocalBean
public class TaskServiceEJB implements TaskService {

	private static Logger logger = Logger.getLogger(TaskServiceEJB.class.getName());
//	// /MSD-S4/data/tasks Task Collection GET toCollection()
//		// /MSD-S4/data/tasks Task Collection POST addIntoCollection(Task)
//		//	/MSD-S4/data/tasks Task Collection DELETE removeFromCollection(Task)
//		//	/MSD-S4/data/tasks/{id} Project GET getById(Integer)
//		//	/MSD-S4/data/tasks/{id} Project PUT add(Task)
//		//	/MSD-S4/data/tasks/{id} Project DELETE remove(Integer)
//		
//		@Override
//		@GET 					/* MSD-S2/rest/tasks 		REST-resource: tasks-collection*/
//		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//		public Collection<Task> toCollection() {
//			logger.info("**** DEBUG REST toCollection()");
//			return super.toCollection();
//		}
//		
//		
//		@Override
//		@GET @Path("/{id}") 	/* MSD-S2/rest/tasks/data/{id} 	REST-resource: task-entity*/
//		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//		public Task getById(@PathParam("id") Integer id) {
//			Task task = super.getById(id);
//			logger.info("**** DEBUG REST getById(" + id +") = " + task);
//			return task;
//		}
//		
//		@POST 					/* MSD-S2/rest/tasks 		REST-resource: tasks-collection*/
//		@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//		public Collection<Task> addIntoCollection(Task task) {
//			// save aggregate
//			super.add(task);
//			logger.info("**** DEBUG REST save aggregate POST");
//			// return updated collection
//			return super.toCollection();
//		}
//		
//		@DELETE 				/* MSD-S2/rest/tasks 		REST-resource: tasks-collection*/
//		@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
//		public Collection<Task> removeFromCollection(Task task) {
//			logger.info("DEBUG: called REMOVE - project: " + task);
//			super.remove(task);
//			return super.toCollection();
//		}
//		
//		
//		@DELETE @Path("/{id}") 	/* MSD-S2/rest/tasks/{id} 	REST-resource: task-entity*/	
//		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
//		public void remove(@PathParam("id")Integer id) {
//			logger.info("DEBUG: called REMOVE - ById() for employees >>>>>>>>>>>>>> simplified ! + id");
//			Task task = super.getById(id);
//			super.remove(task);
//		}
//		
//		@PUT @Path("/{id}") 	/* MSD-S2/rest/tasks/{id} 	REST-resource: task-entity*/	
//		@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
//		@Override
//		public Task add(Task task) {
//			// restore aggregation-relation
//			
//			//logger.info("**** DEBUG REST restore aggregation-relation PUT");
//			// save aggregate
//			//logger.info("**** DEBUG REST save aggregate PUT");
//			task = super.add(task);
//			// return updated collection	
//			return task;
//		}
		

	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public TaskServiceEJB() {		
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		
	
	@PUT @Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })		
	@Override
	public Task addTask(Task taskToAdd) {
		em.persist(taskToAdd);
		em.flush();
		em.refresh(taskToAdd);
		return taskToAdd;
	}

	@DELETE 		
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public String removeTask(Task taskToDelete) {
		taskToDelete = em.merge(taskToDelete);
		em.remove(taskToDelete);
		em.flush();
		return "True";
	}
	
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Task getTaskById(Integer idPerson) {
		return em.find(Task.class, idPerson);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public List<Task> getTasks() {
		List<Task> Tasks = em.createQuery("SELECT t FROM Task t ", Task.class)
				.getResultList();
		return Tasks;
	}	

	@Override
	public Project getProjectById(Integer idProject) {
		return em.find(Project.class, idProject);
	}

	@Override
	public String getMessage() {
		return "TasksServiceEJB is ON... ";
	}
}
