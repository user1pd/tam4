package org.app.service.ejb;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Aplicant;
import org.app.service.entities.Internship;

@Stateless @LocalBean
public class InternshipServiceEJB implements InternshipService {

	private static Logger logger = Logger.getLogger(InternshipServiceEJB.class.getName());


	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public InternshipServiceEJB() {		
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		

	@Override
	public Internship addInternship(Internship internshipToAdd) {
		em.persist(internshipToAdd);
		em.flush();
		em.refresh(internshipToAdd);
		return internshipToAdd;
	}

	@Override
	public String removeInternship(Internship internshipToDelete) {
		internshipToDelete = em.merge(internshipToDelete);
		em.remove(internshipToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Internship getInternshipById(Integer idInternship) {
		return em.find(Internship.class, idInternship);
	}

	@Override
	public List<Internship> getInternships() {
		List<Internship> internships = em.createQuery("SELECT i FROM Internship i ", Internship.class)
				.getResultList();
		return internships;
	}

	

	@Override
	public String getMessage() {
		return "InternshipsServiceEJB is ON... ";
	}
	
	@Override
	public Aplicant getAplicantById(Integer idPerson) {
		return em.find(Aplicant.class, idPerson);
	}
	@Override
	public List<Aplicant> getAplicants() {
		List<Aplicant> aplicants = em.createQuery("SELECT a FROM Aplicant a ", Aplicant.class)
				.getResultList();
		return aplicants;
	}

}
	