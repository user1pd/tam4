package org.app.service.ejb;

import java.util.Collection;
import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Aplicant;
import org.app.service.entities.Project;
@Remote
public interface AplicantService {
	
	// create or update
   Aplicant addAplicant(Aplicant AplicantToAdd);	   
   String removeAplicant(Aplicant AplicantToDelete);
	   
    Aplicant getAplicantByAplicantId(Integer AplicantId);
    Collection<Aplicant> getAplicants();
    
    String getMessage();

}
