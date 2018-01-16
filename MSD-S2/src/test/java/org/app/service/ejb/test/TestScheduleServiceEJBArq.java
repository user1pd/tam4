package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.InternshipService;
import org.app.service.ejb.ScheduleService;
import org.app.service.ejb.ScheduleServiceEJB;
import org.app.service.entities.Internship;
import org.app.service.entities.Schedule;
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
public class TestScheduleServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestScheduleServiceEJBArq.class.getName());

	@EJB // EJB DataService Ref
	private static ScheduleService service;
	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Schedule.class.getPackage())
				.addClass(ScheduleService.class)
				.addClass(ScheduleServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Schedule Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test2_DeleteSchedule() {
		logger.info("DEBUG: Junit TESTING: testDeleteSchedules ...");

		Collection<Schedule> Schedules = service.getSchedules();
		for (Schedule i : Schedules)
			service.removeSchedule(i);
		Collection<Schedule> SchedulesAfterDelete = service.getSchedules();
		assertTrue("Fail to read Schedules!", SchedulesAfterDelete.size() == 0);
  }
	@Test
	public void test3_AddSchedule() {
		logger.info("DEBUG: Junit TESTING: testAddSchedule ...");

		Integer ScheduleToAdd = 3;
		Internship internshipToSchedule = service.getInternships().get(0);
		
		for (int i = 1; i <= ScheduleToAdd; i++) {
			service.addSchedule(new Schedule(null, i, "10:00", "16:00", internshipToSchedule));
		}
		List<Schedule> schedules = service.getSchedules();
		internshipToSchedule.setSchedule(schedules);
		assertTrue("Fail to add Schedules!", schedules.size() == ScheduleToAdd);
	}

	@Test
	public void test4_GetSchedules() {
		logger.info("DEBUG: Junit TESTING: testGetSchedules ...");

		Collection<Schedule> Schedules = service.getSchedules();
		assertTrue("Fail to read Schedules!", Schedules.size() > 0);
	}


}
