package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Project;

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

	@Override
	public Project addProject(Project ProjectToAdd) {
		em.persist(ProjectToAdd);
		em.flush();
		// transactions are managed by default by container
		em.refresh(ProjectToAdd);
		return ProjectToAdd;
	}

	@Override
	public String removeProject(Project ProjectToDelete) {
		ProjectToDelete = em.merge(ProjectToDelete);
		em.remove(ProjectToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Project getProjectByProjectId(Integer idProject) {
		return em.find(Project.class, idProject);
	}

	@Override
	public Collection<Project> getProjects() {
		List<Project> Projects = em.createQuery("SELECT a FROM Project a ", Project.class)
				.getResultList();
		return Projects;
	}

	

	@Override
	public String getMessage() {
		return "ProjectsServiceEJB is ON... ";
	}

}
