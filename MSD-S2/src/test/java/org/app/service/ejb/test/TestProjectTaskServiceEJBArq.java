package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.EmployeeService;
import org.app.service.ejb.EmployeeServiceEJB;
import org.app.service.ejb.ProjectService;
import org.app.service.ejb.ProjectServiceEJB;
import org.app.service.ejb.ProjectTaskService;
import org.app.service.ejb.ProjectTaskServiceEJB;
import org.app.service.ejb.TaskService;
import org.app.service.ejb.TaskServiceEJB;
import org.app.service.entities.Project;
import org.app.service.entities.Task;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectTaskServiceEJBArq {
	
	private static Logger logger = Logger.getLogger(TestProjectTaskServiceEJBArq.class.getName());
	
	@EJB 
	private static ProjectTaskService service;
	
	// Arquilian infrastructure
	@Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "msd-test.war")
                .addPackage(EntityRepository.class.getPackage())
                .addPackage(Project.class.getPackage())
                .addClass(ProjectTaskService.class)
                .addClass(ProjectTaskServiceEJB.class)
                .addClass(EmployeeService.class)
                .addClass(EmployeeServiceEJB.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	
	// JUnit test methods
	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: testGetMessage ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test2_DeleteProject() {
		logger.info("DEBUG: Junit TESTING: testDeleteProject 4001...");
		Project project = service.getById(4010);  					
		if (project != null)
			service.remove(project);
		
		project = service.getById(4010);  // !!!
		assertNull("Fail to delete Project 4001!", project);
	}
	
	@Test
	public void test3_CreateNewProject(){		
		Project project = service.createNewProject(4010);
		assertNotNull("Fail to create new project in repository!", project);
		
		// update project name
		project.setName("NEW "+project.getName());	
		//update project tasks attributes
		List<Task> tasks = project.getTasks();
		for(Task t:tasks) {
			t.setEvolution("100% (completed)");
			t.setEvaluation(10);
		}	
		project=service.add(project);
				
		assertNotNull("Fail to save new project in repository!", project);
		logger.info("DEBUG createNewProject: project changed: " + project);
		// check read
		project = service.getById(4010);  // !!!
		assertNotNull("Fail to find changed project in repository!", project);
		logger.info("DEBUG createNewProject: queried project" + project);
	}	
	
	
	
	@Test
	public void test4_GetProject() {
		logger.info("DEBUG: Junit TESTING: testGetProject 4001 ...");
		Project project = service.getById(4010);
		assertNotNull("Fail to Get Project 4001!", project);
	}
		

	
	
}
