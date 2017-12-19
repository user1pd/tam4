package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Aplicant;
import org.app.service.entities.Internship;

@Remote
public interface InternshipService {
	
		// create or update
	   Internship addInternship(Internship internshipToAdd);
	   
	   String removeInternship(Internship internshipToDelete);
	   
       Internship getInternshipByInternshipId(Integer internshipId);
       Collection<Internship> getInternships();
       
       String getMessage();

	Aplicant getAplicantByAplicantId(Integer idPerson);

}
