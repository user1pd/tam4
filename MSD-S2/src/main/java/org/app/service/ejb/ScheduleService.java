package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Schedule;

@Remote
public interface ScheduleService {

	// create or update
   Schedule addSchedule(Schedule ScheduleToAdd);	   
   String removeSchedule(Schedule ScheduleToDelete);
	   
    Schedule getScheduleByScheduleId(Integer ScheduleId);
    Collection<Schedule> getSchedules();
    
    String getMessage();

}
