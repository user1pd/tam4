package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.AplicantService;
import org.app.service.ejb.InternshipService;
import org.app.service.ejb.InternshipServiceEJB;
import org.app.service.entities.Aplicant;
import org.app.service.entities.Employee;
import org.app.service.entities.Internship;
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
public class TestInternshipServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestInternshipServiceEJBArq.class.getName());

	@EJB // EJB DataService Ref
	private static InternshipService service;
	

	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Internship.class.getPackage())
				.addClass(InternshipService.class).addClass(InternshipServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Internship Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test3_AddInternship() {
		logger.info("DEBUG: Junit TESTING: testAddInternship ...");

		Integer internshipToAdd = 3;
		Integer id=3000;
		Aplicant aplicantToHire=service.getAplicantByAplicantId(1001);
				
		for (int i = 1; i <= internshipToAdd; i++) {
			service.addInternship(new Internship(id+i, new Date(), new Date(), aplicantToHire));
		}
		Collection<Internship> internships = service.getInternships();
		assertTrue("Fail to add Internships!", internships.size() == internshipToAdd);
	}

	@Test
	public void test4_GetInternships() {
		logger.info("DEBUG: Junit TESTING: testGetInternships ...");

		Collection<Internship> internships = service.getInternships();
		assertTrue("Fail to read Internships!", internships.size() > 0);
	}


	@Test
	public void test2_DeleteInternship() {
		logger.info("DEBUG: Junit TESTING: testDeleteInternships ...");

		Collection<Internship> internships = service.getInternships();
		for (Internship i : internships)
			service.removeInternship(i);
		Collection<Internship> internshipsAfterDelete = service.getInternships();
		assertTrue("Fail to read Internships!", internshipsAfterDelete.size() == 0);
  }
}
/* http://localhost:8080/SCRUM-S2/data/Client */