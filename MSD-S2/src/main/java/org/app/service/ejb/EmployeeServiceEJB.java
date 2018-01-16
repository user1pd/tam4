package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Employee;
@Path("employees")
@Stateless @LocalBean
public class EmployeeServiceEJB  
//extends EntityRepositoryBase<Employee> 
implements EmployeeService{
	private static Logger logger = Logger.getLogger(EmployeeServiceEJB.class.getName());
	
//	@Override
//	@GET 					/* MSD-S2/rest/employees 		REST-resource: employee-collection*/
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public Collection<Employee> toCollection() {
//		logger.info("**** DEBUG REST toCollection()");
//		return super.toCollection();
//	}
//	@Override
//	@GET @Path("/{id}") 	/* MSD-S2/rest/employees/data/{id} 	REST-resource: employee-entity*/
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public Employee getById(@PathParam("id") Integer id) {
//		Employee employee = super.getById(id);
//		logger.info("**** DEBUG REST getById(" + id +") = " + employee);
//		return employee;
//	}
//	
//	@POST 					/* MSD-S2/rest/tasks 		REST-resource: tasks-collection*/
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//	public Collection<Employee> addIntoCollection(Employee employee) {
//		// save aggregate
//		super.add(employee);
//		logger.info("**** DEBUG REST save aggregate POST");
//		// return updated collection
//		return super.toCollection();
//	}
//	
//	@DELETE 				/* MSD-S2/rest/tasks 		REST-resource: tasks-collection*/
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
//	public Collection<Employee> removeFromCollection(Employee employee) {
//		logger.info("DEBUG: called REMOVE - project: " + employee);
//		super.remove(employee);
//		return super.toCollection();
//	}
//	
//	
//	@DELETE @Path("/{id}") 	/* MSD-S2/rest/tasks/{id} 	REST-resource: task-entity*/	
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
//	public void remove(@PathParam("id")Integer id) {
//		logger.info("DEBUG: called REMOVE - ById() for employees >>>>>>>>>>>>>> simplified ! + id");
//		Employee employee = super.getById(id);
//		super.remove(employee);
//	}
//	
//	@PUT @Path("/{id}") 	/* MSD-S2/rest/tasks/{id} 	REST-resource: task-entity*/	
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
//	@Override
//	public Employee add(Employee employee) {
//		// restore aggregation-relation
//		
//		//logger.info("**** DEBUG REST restore aggregation-relation PUT");
//		// save aggregate
//		//logger.info("**** DEBUG REST save aggregate PUT");
//		employee = super.add(employee);
//		// return updated collection	
//		return employee;
//	}
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public EmployeeServiceEJB() {
		
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		
	
	//CRUD implementation...............................
	//..................................................
	@Override
	public Employee addEmployee(Employee EmployeeToAdd) {
		em.persist(EmployeeToAdd);
		em.flush();
		em.refresh(EmployeeToAdd);
		return EmployeeToAdd;
	}

	@Override
	public String removeEmployee(Employee employeeToDelete) {
		employeeToDelete = em.merge(employeeToDelete);
		em.remove(employeeToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Employee getEmployeeById(Integer idPerson) {
		return em.find(Employee.class, idPerson);
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> Employees = em.createQuery("SELECT a FROM Employee a ", Employee.class)
				.getResultList();
		return Employees;
	}
	@Override
	public Employee getEmployeeByName(String name) {
		return em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class)
				.setParameter("name", name)
				.getSingleResult();
	}
	

	@Override
	public String getMessage() {
		return "EmployeesServiceEJB is ON... ";
	}
}

