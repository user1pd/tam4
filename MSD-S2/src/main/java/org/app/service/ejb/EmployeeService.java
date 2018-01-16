package org.app.service.ejb;


import java.util.List;

import javax.ejb.Remote;

import org.app.service.entities.Employee;

@Remote
public interface EmployeeService {

	// create or update
   Employee addEmployee(Employee employeeToAdd);	
   
   //delete
   String removeEmployee(Employee employeeToDelete);
   
	//read
    Employee getEmployeeById(Integer employeeId);
    List<Employee> getEmployees();
    
    //custom read
    Employee getEmployeeByName(String employeeName);
    
    //
    //others
    String getMessage();
    
	//Collection<Employee> toCollection();
//	Employee getById(Integer id);

}
