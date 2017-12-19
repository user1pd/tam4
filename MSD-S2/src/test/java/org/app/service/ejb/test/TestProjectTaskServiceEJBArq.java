package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.ProjectTaskService;
import org.app.service.ejb.ProjectTaskServiceEJB;
import org.app.service.ejb.ScheduleService;
import org.app.service.ejb.ScheduleServiceEJB;
import org.app.service.ejb.TaskService;
import org.app.service.ejb.TaskServiceEJB;
import org.app.service.entities.Aplicant;
import org.app.service.entities.Project;
import org.app.service.entities.Schedule;
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
	
	@EJB // Test EJB Data Service Reference is injected
	private static ProjectTaskService service;	
	private static TaskService servicet;	
	
	// Arquilian infrastructure
	@Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "msd-test.war")
                .addPackage(EntityRepository.class.getPackage())
                .addPackage(Project.class.getPackage())
                .addClass(TaskService.class).addClass(TaskServiceEJB.class)
                .addClass(ProjectTaskService.class).addClass(ProjectTaskServiceEJB.class)
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
		logger.info("DEBUG: Junit TESTING: testDeleteProject 7002...");
		Project project = service.getById(7002);  // !!!
		
		List<Task> tasks = project.getTasks();
		
//		for (Task i: tasks){
//				servicet.removeTask(i);
//		}
			
		if (project != null)
			service.remove(project)			
			;
		project = service.getById(7002);  // !!!
		assertNull("Fail to delete Project 7002!", project);
	}	
	
	/* CREATE Test 2: create aggregate*/
	@Test
	public void test3_CreateNewProject(){
		Project project = service.createNewProject(7002);
		assertNotNull("Fail to create new project in repository!", project);
		// update project
		project.setName(project.getName() + " - changed");		
		List<Task> tasks = new ArrayList<Task>();
		tasks = project.getTasks();
				
		project = service.add(project);
		assertNotNull("Fail to save new project in repository!", project);
		logger.info("DEBUG createNewProject: project changed: " + project);
		// check read
		project = service.getById(7002);  // !!!
		assertNotNull("Fail to find changed project in repository!", project);
		logger.info("DEBUG createNewProject: queried project" + project);
	}	
	@Test
	public void test4_GetProject() {
		logger.info("DEBUG: Junit TESTING: testGetProject 7002 ...");
		Project project = service.getById(7002);
		assertNotNull("Fail to Get Project 7002!", project);
	}
		

	
	
}
