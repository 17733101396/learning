package com.hainu.hrms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.hainu.hrms.model.Admin;
import com.hainu.hrms.service.AdminService;
import com.hainu.hrms.utils.TokenUtil;


@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger log = Logger.getLogger(AdminController.class);
	@Autowired
	@Qualifier("adminService")
	AdminService adminService;
	@Autowired
	TokenUtil tokenUtil;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestBody Admin admin,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");	
			//Admin admin=new Admin();
			log.debug(admin.getUsername());
			Admin adminResult=adminService.login(admin);
			log.debug(adminResult);
			if (!StringUtils.isEmpty(adminResult)) {
				log.debug("登录成功");
				String token=tokenUtil.createToken(adminResult);
				log.debug(token);
				Map<String, String> map=new HashMap<>();
				map.put("status", "ok");
				map.put("access-token", token);
				map.put("admin_id", adminResult.getId().toString());
				map.put("role_name", adminResult.getRole_name());
				String jsonString=JSON.toJSONString(map);
				log.debug(jsonString);
				response.getWriter().write(jsonString);
			}else {
				log.debug("登录失败");
				response.setStatus(401);
				response.getWriter().write(401);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("登录异常");
		}
		return null;
	}
	
	@RequestMapping(value="/selectAdmins",method=RequestMethod.GET)
	public String selectAdmins(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			List<Admin> list = adminService.selectAdmins();
			String adminResult=JSON.toJSONString(list);
			response.getWriter().write(adminResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/deleteAdmins",method=RequestMethod.POST)
	public String deleteAdmins(@RequestBody Map<String,List<Integer>> map,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
    		if (map.get("id")==null) {
				log.debug("空");
				return null;
			}
    	   	log.debug(map);
			int res=adminService.deleteAdmins(map.get("id"));		
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/addAdmin",method=RequestMethod.POST)
	public String addAdmin(@RequestBody Admin admin,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=adminService.addAdmin(admin);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/updateAdmin",method=RequestMethod.POST)
	public String updateAdmin(@RequestBody Admin admin,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=adminService.updateAdmin(admin);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	
}
