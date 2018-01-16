package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.ProjectService;
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
public class TestTaskServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestTaskServiceEJBArq.class.getName());

	@EJB // EJB DataService Ref
	private static TaskService service;

	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Task.class.getPackage())
				.addClass(TaskService.class)
				.addClass(TaskServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}


	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test2_DeleteTask() {
		logger.info("DEBUG: Junit TESTING: testDeleteTasks ...");

		Collection<Task> Tasks = service.getTasks();
		for (Task t : Tasks)
			service.removeTask(t);
		Collection<Task> TasksAfterDelete = service.getTasks();
		assertTrue("Fail to read Tasks!", TasksAfterDelete.size() == 0);
  }
	@Test
	public void test3_AddTask() {
		logger.info("DEBUG: Junit TESTING: testAddTask ...");

		Integer taskToAdd = 3;
		Project projectToEdit = service.getProjectById(4002);
		for (int i = 1; i <= taskToAdd; i++) {
			service.addTask(new Task(null, "T: "+projectToEdit.getIdProject()+"."+i+i,projectToEdit));
		}
		
		Collection<Task> tasks = service.getTasks();
		projectToEdit.getTasks().addAll(tasks);
	}

	@Test
	public void test4_GetTasks() {
		logger.info("DEBUG: Junit TESTING: testGetTasks ...");

		Collection<Task> Tasks = service.getTasks();
		assertTrue("Fail to read Tasks!", Tasks.size() > 0);
	}


}
