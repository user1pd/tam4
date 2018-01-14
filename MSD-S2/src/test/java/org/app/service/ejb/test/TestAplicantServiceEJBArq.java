package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.AplicantService;
import org.app.service.ejb.AplicantServiceEJB;
import org.app.service.entities.Aplicant;
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
public class TestAplicantServiceEJBArq {
	
	private static Logger logger = Logger.getLogger(TestAplicantServiceEJBArq.class.getName());

	@EJB // EJB DataService Ref
	private static AplicantService service;

	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Aplicant.class.getPackage())
				.addClass(AplicantService.class).addClass(AplicantServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Aplicant Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test3_AddAplicant() {
		logger.info("DEBUG: Junit TESTING: testAddAplicant ...");

		Integer AplicantsToAdd = 3;
		Integer id=1000;
		for (int i = 1; i <= AplicantsToAdd; i++) {
			service.addAplicant(new Aplicant(id+i, "Person "+id+i, "mail"+id+i+"a@dam.com", "07513"+id+i, new Date(), "user"+id+i+"a", "password"+id+i,"FEAA", "UAIC"));
		}
		Collection<Aplicant> Aplicants = service.getAplicants();
		assertTrue("Fail to add Aplicants!", Aplicants.size() == AplicantsToAdd);
	}

	@Test
	public void test4_GetAplicants() {
		logger.info("DEBUG: Junit TESTING: testGetAplicants ...");

		Collection<Aplicant> Aplicants = service.getAplicants();
		assertTrue("Fail to read Aplicants!", Aplicants.size() > 0);
	}

	@Test
	public void test2_DeleteAplicant() {
		logger.info("DEBUG: Junit TESTING: testDeleteAplicants ...");

		Collection<Aplicant> Aplicants = service.getAplicants();
		for (Aplicant a : Aplicants)
			service.removeAplicant(a);
		Collection<Aplicant> AplicantsAfterDelete = service.getAplicants();
		assertTrue("Fail to read Aplicants!", AplicantsAfterDelete.size() == 0);
  }

}
