package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Employee;
import org.app.service.entities.Task;

@Remote
public interface EmployeeService {

	// create or update
   Employee addEmployee(Employee EmployeeToAdd);	   
   String removeEmployee(Employee EmployeeToDelete);
	   
    Employee getEmployeeByEmployeeId(Integer EmployeeId);
    Collection<Employee> getEmployees();
    
    String getMessage();
	Collection<Employee> toCollection();
	Employee getById(Integer id);

}
