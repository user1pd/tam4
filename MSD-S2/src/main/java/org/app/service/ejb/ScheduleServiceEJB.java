package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Schedule;

@Stateless @LocalBean
public class ScheduleServiceEJB implements ScheduleService{

	private static Logger logger = Logger.getLogger(ScheduleServiceEJB.class.getName());


	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public ScheduleServiceEJB() {
		
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		

	@Override
	public Schedule addSchedule(Schedule ScheduleToAdd) {
		em.persist(ScheduleToAdd);
		em.flush();
		// transactions are managed by default by container
		em.refresh(ScheduleToAdd);
		return ScheduleToAdd;
	}

	@Override
	public String removeSchedule(Schedule ScheduleToDelete) {
		ScheduleToDelete = em.merge(ScheduleToDelete);
		em.remove(ScheduleToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Schedule getScheduleByScheduleId(Integer idPerson) {
		return em.find(Schedule.class, idPerson);
	}

	@Override
	public Collection<Schedule> getSchedules() {
		List<Schedule> Schedules = em.createQuery("SELECT a FROM Schedule a ", Schedule.class)
				.getResultList();
		return Schedules;
	}

	

	@Override
	public String getMessage() {
		return "SchedulesServiceEJB is ON... ";
	}
}
