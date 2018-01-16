package org.app.service.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.app.service.entities.Internship;
import org.app.service.entities.Schedule;

@Remote
public interface ScheduleService {

	// create or update
   Schedule addSchedule(Schedule ScheduleToAdd);	
   
   //delete
   String removeSchedule(Schedule ScheduleToDelete);
   
	//read   
    Schedule getScheduleById(Integer ScheduleId);
    List<Schedule> getSchedules();
    
    //others
    String getMessage();
    
    List<Internship> getInternships();

}
