package com.hainu.hrms.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hainu.hrms.model.Employee;


@Repository
public interface EmployeeMapper {
	
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
