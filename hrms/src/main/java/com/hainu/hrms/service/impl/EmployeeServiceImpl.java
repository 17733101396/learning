package com.hainu.hrms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hainu.hrms.mapper.EmployeeMapper;
import com.hainu.hrms.model.Employee;
import com.hainu.hrms.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	@Override
	public List<Employee> selectEmployees() {
		// TODO Auto-generated method stub
		return employeeMapper.selectEmployees();
	}

	@Override
	public Integer deleteEmployees(List<Long> list) {
		// TODO Auto-generated method stub
		return employeeMapper.deleteEmployees(list);
	}

	@Override
	public Integer addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeMapper.addEmployee(employee);
	}

	@Override
	public Integer updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeMapper.updateEmployee(employee);
	}

}
