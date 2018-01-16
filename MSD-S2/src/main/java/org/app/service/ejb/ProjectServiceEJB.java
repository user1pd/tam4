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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Project;
import org.app.service.entities.Task;
@Path("projects")
@Stateless @LocalBean
public class ProjectServiceEJB implements ProjectService{

	private static Logger logger = Logger.getLogger(ProjectServiceEJB.class.getName());


	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public ProjectServiceEJB() {		
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		

	//......................................CREATE
	@PUT @Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })		
	@Override
	public Project addProject(Project ProjectToAdd) {
		em.persist(ProjectToAdd);
		em.flush();
		// transactions are managed by default by container
		em.refresh(ProjectToAdd);
		return ProjectToAdd;
	}

	//......................................DELETE
	@DELETE 		
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public String removeProject(Project ProjectToDelete) {
		ProjectToDelete = em.merge(ProjectToDelete);
		em.remove(ProjectToDelete);
		em.flush();
		return "True";
	}
	//......................................READ
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Project getProjectById(@PathParam("id")Integer id) {
		return em.find(Project.class, id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public List<Project> getProjects() {
		List<Project> Projects = em.createQuery("SELECT a FROM Project a ", Project.class)
				.getResultList();
		return Projects;
	}
	
//	@GET @Path("/{id}")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Task getTaskById(Integer id) {
		return em.find(Task.class, id);
	}

	
	//......................................
	@Override
	public String getMessage() {
		return "ProjectsServiceEJB is ON... ";
	}

}
