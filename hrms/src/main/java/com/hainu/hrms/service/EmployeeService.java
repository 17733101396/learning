package com.hainu.hrms.service;

import java.util.List;
import com.hainu.hrms.model.Employee;

public interface EmployeeService {

	/**selectEmployees
	 * 
	 * @param Employee的list
	 * @return
	 */
	public List<Employee> selectEmployees();
	
	/**deleteEmployee
	 * 
	 * @param id的list
	 * @return
	 */
	public Integer deleteEmployees(List<Long> list);
	
	
	/**addEmployee
	 * 
	 * @param employee
	 * @return
	 */
	public Integer addEmployee(Employee employee);
	
	/**updateEmployee
	 * 
	 * @param employee
	 * @return
	 */
	public Integer updateEmployee(Employee employee);
}
