package com.hainu.hrms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hainu.hrms.mapper.DepartmentMapper;
import com.hainu.hrms.model.Department;
import com.hainu.hrms.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Override
	public List<Department> selectDepartments() {
		// TODO Auto-generated method stub
		return departmentMapper.selectDepartments();
	}

	@Override
	public Integer deleteDepartments(List<Integer> list) {
		// TODO Auto-generated method stub
		return departmentMapper.deleteDepartments(list);
	}

	@Override
	public Integer addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentMapper.addDepartment(department);
	}

	@Override
	public Integer updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentMapper.updateDepartment(department);
	}

}
