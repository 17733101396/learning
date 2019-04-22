package com.hainu.hrms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.hainu.hrms.model.Department;
import com.hainu.hrms.service.DepartmentService;


@Controller
@RequestMapping("/department")
public class DepartmentController {
	private static final Logger log = Logger.getLogger(DepartmentController.class);
	@Autowired
	@Qualifier("departmentService")
	DepartmentService departmentService;
	
	@RequestMapping(value="/selectDepartments",method=RequestMethod.GET)
	public String selectDepartments(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			List<Department> list = departmentService.selectDepartments();
			String DepartmentResult=JSON.toJSONString(list);
			response.getWriter().write(DepartmentResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/deleteDepartments",method=RequestMethod.POST)
	public String deleteDepartments(@RequestBody Map<String,List<Integer>> map,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			if (map.get("id")==null) {
				log.debug("空");
				return null;
			}
    	   	log.debug(map);
			int res=departmentService.deleteDepartments(map.get("id"));		
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/addDepartment",method=RequestMethod.POST)
	public String addDepartment(@RequestBody Department department,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=departmentService.addDepartment(department);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/updateDepartment",method=RequestMethod.POST)
	public String updateDepartment(@RequestBody Department department,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=departmentService.updateDepartment(department);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	
}
