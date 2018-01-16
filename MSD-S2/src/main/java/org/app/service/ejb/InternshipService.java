package org.app.service.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.app.service.entities.Aplicant;
import org.app.service.entities.Internship;

@Remote
public interface InternshipService {
	
		// create or update
	   Internship addInternship(Internship internshipToAdd);
	   
	   //delete
	   String removeInternship(Internship internshipToDelete);
	   
	   //read
       Internship getInternshipById(Integer internshipId);
       List<Internship> getInternships();
       
       //others
       String getMessage();

       Aplicant getAplicantById(Integer idPerson);
       List<Aplicant> getAplicants();

}
