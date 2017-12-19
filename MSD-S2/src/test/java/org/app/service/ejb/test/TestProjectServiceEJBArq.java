package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.ProjectService;
import org.app.service.ejb.ProjectServiceEJB;
import org.app.service.entities.Project;
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
public class TestProjectServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestProjectServiceEJBArq.class.getName());

	@EJB // EJB DataService Ref
	private static ProjectService service;

	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Project.class.getPackage())
				.addClass(ProjectService.class).addClass(ProjectServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Project Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test3_AddProject() {
		logger.info("DEBUG: Junit TESTING: testAddProject ...");

		Integer ProjectToAdd = 3;
		Integer id=6000;
		for (int i = 1; i <= ProjectToAdd; i++) {
			service.addProject(new Project(id+i, "P"+i, new Date()));
		}
		Collection<Project> Projects = service.getProjects();
		assertTrue("Fail to add Projects!", Projects.size() == ProjectToAdd);
	}

	@Test
	public void test4_GetProjects() {
		logger.info("DEBUG: Junit TESTING: testGetProjects ...");

		Collection<Project> Projects = service.getProjects();
		assertTrue("Fail to read Projects!", Projects.size() > 0);
	}

	@Test
	public void test2_DeleteProject() {
		logger.info("DEBUG: Junit TESTING: testDeleteProjects ...");

		Collection<Project> Projects = service.getProjects();
		for (Project i : Projects)
			service.removeProject(i);
		Collection<Project> ProjectsAfterDelete = service.getProjects();
		assertTrue("Fail to read Projects!", ProjectsAfterDelete.size() == 0);
  }

}
