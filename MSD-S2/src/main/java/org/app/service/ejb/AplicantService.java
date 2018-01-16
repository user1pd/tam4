package org.app.service.ejb;


import java.util.List;

import javax.ejb.Remote;

import org.app.service.entities.Aplicant;
@Remote
public interface AplicantService {
	
	// create or update
   Aplicant addAplicant(Aplicant aplicantToAdd);	
   
   //delete
   String removeAplicant(Aplicant aplicantToDelete);
	
   //read
    Aplicant getAplicantById(Integer aplicantId);
    List<Aplicant> getAplicants();
    
    //custom read
    Aplicant getAplicantByName(String aplicantName);
    
    String getMessage();

}
