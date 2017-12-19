package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Aplicant;
import org.app.service.entities.Project;

@Stateless @LocalBean
public class AplicantServiceEJB  implements AplicantService{
	
	private static Logger logger = Logger.getLogger(AplicantServiceEJB.class.getName());


	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public AplicantServiceEJB() {
		
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		

	@Override
	public Aplicant addAplicant(Aplicant AplicantToAdd) {
		em.persist(AplicantToAdd);
		em.flush();
		// transactions are managed by default by container
		em.refresh(AplicantToAdd);
		return AplicantToAdd;
	}

	@Override
	public String removeAplicant(Aplicant AplicantToDelete) {
		AplicantToDelete = em.merge(AplicantToDelete);
		em.remove(AplicantToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Aplicant getAplicantByAplicantId(Integer idPerson) {
		return em.find(Aplicant.class, idPerson);
	}

	@Override
	public Collection<Aplicant> getAplicants() {
		List<Aplicant> Aplicants = em.createQuery("SELECT a FROM Aplicant a ", Aplicant.class)
				.getResultList();
		return Aplicants;
	}

	

	@Override
	public String getMessage() {
		return "AplicantsServiceEJB is ON... ";
	}
}
