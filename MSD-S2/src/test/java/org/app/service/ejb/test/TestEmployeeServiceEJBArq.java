package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.EmployeeService;
import org.app.service.ejb.EmployeeServiceEJB;
import org.app.service.entities.Employee;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestEmployeeServiceEJBArq.class.getName());

	@EJB // EJB DataService Ref
	private static EmployeeService service;

	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Employee.class.getPackage())
				.addClass(EmployeeService.class).addClass(EmployeeServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Employee Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test3_AddEmployee() {
		logger.info("DEBUG: Junit TESTING: testAddEmployee ...");

		Integer EmployeeToAdd = 3;
		Integer id=2000;
		for (int i = 1; i <= EmployeeToAdd; i++) {
			service.addEmployee(new Employee(id+i, "Person"+i+i, "user"+i+i));
		}
		Collection<Employee> Employees = service.getEmployees();
		assertTrue("Fail to add Employees!", Employees.size() == EmployeeToAdd);
	}

	@Test
	public void test4_GetEmployees() {
		logger.info("DEBUG: Junit TESTING: testGetEmployees ...");

		Collection<Employee> Employees = service.getEmployees();
		assertTrue("Fail to read Employees!", Employees.size() > 0);
	}

	@Test
	public void test2_DeleteEmployee() {
		logger.info("DEBUG: Junit TESTING: testDeleteEmployees ...");

		Collection<Employee> Employees = service.getEmployees();
		for (Employee i : Employees)
			service.removeEmployee(i);
		Collection<Employee> EmployeesAfterDelete = service.getEmployees();
		assertTrue("Fail to read Employees!", EmployeesAfterDelete.size() == 0);
  }

}
