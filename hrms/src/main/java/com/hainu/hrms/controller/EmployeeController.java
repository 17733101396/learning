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
import com.hainu.hrms.model.Employee;
import com.hainu.hrms.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private static final Logger log = Logger.getLogger(EmployeeController.class);
	@Autowired
	@Qualifier("employeeService")
	EmployeeService employeeService;
	
	@RequestMapping(value="/selectEmployees",method=RequestMethod.GET)
	public String selectEmployees(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			List<Employee> list = employeeService.selectEmployees();
			String EmployeeResult=JSON.toJSONString(list);
			response.getWriter().write(EmployeeResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/deleteEmployees",method=RequestMethod.POST)
	public String deleteEmployees(@RequestBody Map<String,List<Long>> map,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			if (map.get("id")==null) {
				log.debug("空");
				return null;
			}
    	   	log.debug(map);
			int res=employeeService.deleteEmployees(map.get("id"));		
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/addEmployee",method=RequestMethod.POST)
	public String addEmployee(@RequestBody Employee employee,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=employeeService.addEmployee(employee);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/updateEmployee",method=RequestMethod.POST)
	public String updateEmployee(@RequestBody Employee employee,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=employeeService.updateEmployee(employee);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	
}
