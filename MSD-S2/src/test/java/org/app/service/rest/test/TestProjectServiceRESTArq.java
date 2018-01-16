package org.app.service.rest.test;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import javax.ws.rs.client.Entity;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.ProjectService;
import org.app.service.entities.Project;
import org.app.service.rest.ApplicationConfig;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
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
public class TestProjectServiceRESTArq {
	private static Logger logger = Logger.getLogger(TestProjectServiceRESTArq.class.getName());
	private static String serviceURL = "http://localhost:8080/msd-s2-test/data/projects"; 
	
	@Deployment // Arquilian infrastructure 
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "msd-s2-test.war")
				.addPackage(Project.class.getPackage())
				.addPackage(ProjectService.class.getPackage())
				.addPackage(EntityRepository.class.getPackage())
				.addPackage(ApplicationConfig.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml"); 
		}
	@Test 
	public void test1_GetByID() { 
		String resourceURL = serviceURL + "/1"; 
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		Project project = ClientBuilder.newClient().target(resourceURL) 
				.request().accept(MediaType.APPLICATION_JSON) 
				.get().readEntity(Project.class);
		assertNotNull("REST Data Service failed!", project); 
		logger.info(">>>>>> DEBUG: REST Response ..." + project);
	}
	
	@Test
	public void test3_AddProject() {
		// addIntoCollection
		logger.info("DEBUG: Junit TESTING: test3_AddProject ...");
		Client client = ClientBuilder.newClient();
		Collection<Project> projects;
		Integer projectsToAdd = 3;
		Project project;
		for (int i=1; i <= projectsToAdd; i++){
			project = new Project(i, "Project_" + (100 + i), new Date());
			projects = client.target(serviceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(project, MediaType.APPLICATION_JSON))
				.readEntity(new GenericType<Collection<Project>>(){});
			assertTrue("Fail to read Projects!", projects.size() > 0);
		}
		projects = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Project>>(){});
		assertTrue("Fail to add Projects!", projects.size() >= projectsToAdd);
		projects.stream().forEach(System.out::println);
	}

	
	@Test
	public void test4_GetProjects() {
		logger.info("DEBUG: Junit TESTING: test4_GetProjects ...");
		Collection<Project> projects = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Project>>(){});
		assertTrue("Fail to read Projects!", projects.size() > 0);
		projects.stream().forEach(System.out::println);
	}
	
	@Test
	public void test3_CreateProject() {
		String resourceURL = serviceURL + "/new/"; //createNewProject
		logger.info("DEBUG: Junit TESTING: test3_CreateProject ...");
		Client client = ClientBuilder.newClient();
		
		Integer projectsToAdd = 3;
		Project project;
		for (int i=1; i <= projectsToAdd; i++){
			project = ClientBuilder.newClient().target(resourceURL + i)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(null).readEntity(Project.class);
			System.out.println(">>> Created/posted Project: " + project);
		}

		Collection<Project> projects = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Project>>(){});
		
		assertTrue("Fail to add Projects!", projects.size() >= projectsToAdd);
	}	

//	@Test
//	public void test2_DeleteProject() {
//		String resourceURL = serviceURL + "/";
//		logger.info("DEBUG: Junit TESTING: test2_DeleteProject ...");
//		Client client = ClientBuilder.newClient();
//		Collection<Project> projects = client.target(serviceURL)
//				.request().get()
//				.readEntity(new GenericType<Collection<Project>>(){});		
//		
//		for (Project p: projects) {
//			client.target(resourceURL + p.getIdProject().request().delete();
//		}
//		
//		Collection<Project> projectsAfterDelete = client.target(serviceURL)
//				.request().get()
//				.readEntity(new GenericType<Collection<Project>>(){});	
//		assertTrue("Fail to read Projects!", projectsAfterDelete.size() == 0);
//	}
	
	@Test
	public void test5_UpdateProject() {
		String resourceURL = serviceURL + "/1"; //createNewProject
		logger.info("************* DEBUG: Junit TESTING: test5_UpdateProject ... :" + resourceURL);
		Client client = ClientBuilder.newClient();
		// Get project
		Project project = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Project.class);
		
		assertNotNull("REST Data Service failed!", project);
		logger.info(">>> Initial Project: " + project);
		
		// update and save project
		project.setName(project.getName() + "_UPD_JSON");
		project = client.target(resourceURL)
				//.request().accept(MediaType.APPLICATION_XML).header("Content-Type", "application/xml")
				.request().accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(project, MediaType.APPLICATION_JSON))
				.readEntity(Project.class);
		
		logger.info(">>> Updated Project: " + project);
		
		assertNotNull("REST Data Service failed!", project);
	}	

}