package com.hainu.hrms.service;

import java.util.List;

import com.hainu.hrms.model.Department;


public interface DepartmentService {
	/**selectDepartments
	 * 
	 * @param 
	 * @return Department的list
	 */
	public List<Department> selectDepartments();
	
	/**deleteDepartments
	 * 
	 * @param id的list
	 * @return Integer
	 */
	public Integer deleteDepartments(List<Integer> list);
	
		
	/**addDepartment
	 * 
	 * @param department
	 * @return Integer
	 */
	public Integer addDepartment(Department department);
	
	/**updateDepartment
	 * 
	 * @param department
	 * @return Integer
	 */
	public Integer updateDepartment(Department department);
}
