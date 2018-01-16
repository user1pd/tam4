package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Aplicant;

@Stateless @LocalBean
public class AplicantServiceEJB  implements AplicantService{
	
	private static Logger logger = Logger.getLogger(AplicantServiceEJB.class.getName());

	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public AplicantServiceEJB() {		
	}
	
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		
	
	//..CRUD implementation............................
	//.................................................
	@Override
	public Aplicant addAplicant(Aplicant aplicantToAdd) {
		em.persist(aplicantToAdd);
		em.flush();
		em.refresh(aplicantToAdd);
		return aplicantToAdd;
	}

	@Override
	public String removeAplicant(Aplicant aplicantToDelete) {
		aplicantToDelete = em.merge(aplicantToDelete);
		em.remove(aplicantToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Aplicant getAplicantById(Integer id) {
		return em.find(Aplicant.class, id);
	}

	@Override
	public List<Aplicant> getAplicants() {
		List<Aplicant> aplicants = em.createQuery("SELECT a FROM Aplicant a ", Aplicant.class)
				.getResultList();
		return aplicants;
	}
	
	@Override
	public Aplicant getAplicantByName(String name) {
		return em.createQuery("SELECT a FROM Aplicant a WHERE a.name= :name", Aplicant.class)
				.setParameter("name",name)
				.getSingleResult();
	}	

	@Override
	public String getMessage() {
		return "AplicantsServiceEJB is ON... ";
	}
}
