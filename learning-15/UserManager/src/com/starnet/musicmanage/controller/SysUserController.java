package com.starnet.musicmanage.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.starnet.musicmanage.model.SysUser;
import com.starnet.musicmanage.service.ISysUserService;

/**
 * 处理用户请求 Controller
 **/
@Controller
public class SysUserController {
	
	private static final Logger log = Logger.getLogger(SysUserController.class);
	
	/**
     * 自动注入ISysUserService
     **/
	@Autowired
	@Qualifier(value="sysUserServiceImpl")
    private ISysUserService sysUserService;
	
	//登录
	@RequestMapping("/login")
    public ModelAndView login(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception  {
		try {
			arg1.setHeader("Content-type", "text/html;charset=utf-8");
	    	OutputStream out = arg1.getOutputStream();
			String userName = arg0.getParameter("userName");
			String password = arg0.getParameter("password");
			
			//检查用户名和密码是否为空
			if (StringUtils.isNullOrEmpty(userName)) {
				log.debug("用户名为空");
				out.write("0".getBytes());
			}
			if (StringUtils.isNullOrEmpty(password)) {
				log.debug("密码为空");
				out.write("0".getBytes());
			}
			//调用login方法,超级管理员返回1,一般管理员返回2,失败返回0
			int ret = sysUserService.login(userName, password);
			arg0.getSession().setAttribute("paramUserName", userName);
			arg0.getSession().setAttribute("paramPower", ret);
			out.write(String.valueOf(ret).getBytes());
			log.debug("登录返回结果sysUserService.login(userName, password):"+ret);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-login异常");
		}
    	
		return null;
    }
	
    //跳转至管理页面
    @RequestMapping("/toManage")
    public ModelAndView toManage(HttpServletRequest arg0,HttpServletResponse arg1) {
    	try {
    		ModelAndView mav = new ModelAndView();
        	mav.setViewName("manage.jsp");
        	return mav;//跳转到manage.jsp
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-toManage异常");
		}
		return null;
    	
    }
    
    //获取全部用户信息
    @RequestMapping("/listAllUser")
    public ModelAndView listAllUser(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception{
    	try {
    		arg1.setHeader("Content-type", "text/json;charset=utf-8");
    		OutputStream out = arg1.getOutputStream();
    		//调用listAll方法并把结果转化为JSONArray对象
    		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(this.sysUserService.listAll());
    		//jsonArray对象转化为json字符串
    		byte[] retArr=jsonArray.toJSONString().getBytes("utf-8");
    		//响应字符串给请求页面
    		out.write(retArr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-listAllUser异常");
		}
    	
		return null;
    }
    
    //添加用户
    @RequestMapping("/addUser")
    public ModelAndView addUser(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception {
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
    		String userName = arg0.getParameter("userName");
    		String password = arg0.getParameter("password");
    		String sex = arg0.getParameter("sex");
    		String age = arg0.getParameter("age");
    		String phone = arg0.getParameter("phone");
    		String address = arg0.getParameter("address");
    		String power = arg0.getParameter("power");
    		//检查用户名密码是否为空
    		if (StringUtils.isNullOrEmpty(userName)) {
    			log.debug("用户名为空");
    			arg1.getOutputStream().write("0".getBytes());
    			return null;
    		}
    		if (StringUtils.isNullOrEmpty(password)) {
    			log.debug("密码为空");
    			arg1.getOutputStream().write("0".getBytes());
    			return null;
    		}
    		//把信息添加进sysUser对象
    		SysUser sysUser = new SysUser();
    		sysUser.setUserName(userName);
    		sysUser.setPassword(password);
    		sysUser.setSex(sex);
    		//检查年龄是否为正整数,不是则置0
    		if ((!StringUtils.isNullOrEmpty(age))&& age.matches("[0-9]*")) {
    			sysUser.setAge(Integer.parseInt(age));
    		} else {
    			log.debug("年龄不是自然数");
    			sysUser.setAge(0);
    		}
    		sysUser.setPhone(phone);
    		sysUser.setAddress(address);
    		sysUser.setPower(power);
    		//调用addSysUser方法添加用户,成功返回1
    		int ret = sysUserService.addSysUser(sysUser);
    		log.debug("插入用户返回结果sysUserService.addSysUser(sysUser):"+ret);
    		//返回结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-addUser异常");
		}
    	
		return null;
	}
    
    //删除用户
    @RequestMapping("/deleteUsers")
    public ModelAndView deleteUsers(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception {
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
    		//获取请求id数组
    		String[] ids=arg0.getParameterValues("ids[]");
    		//检查数组是否为空
    		if (ids==null) {
    			log.debug("ids为空");
    			return null;
    		}
    	    //创建Long型的List,并把ids数组转化后添加进去
    		List<Long> idList = new ArrayList<Long>();
    		for (String id : ids) {
    			idList.add(Long.valueOf(id));
    		}
    		
    		//调用deleteUsers方法,成功返回1
    		int ret = sysUserService.deleteUsers(idList);
    		log.debug("删除用户返回结果sysUserService.deleteUsers(idList):"+ret);
    		//响应结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-deleteUsers异常");
		}
    	
		return null;
	}
    
    //更新用户
    @RequestMapping("/updateUser")
    public ModelAndView updateUser(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception {
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
    		String id = arg0.getParameter("id");
    		String userName = arg0.getParameter("userName");
    		String sex = arg0.getParameter("sex");
    		String age = arg0.getParameter("age");
    		String phone = arg0.getParameter("phone");
    		String address = arg0.getParameter("address");
    		String password = arg0.getParameter("password");
    		String power = arg0.getParameter("power");
    		//添加数据到sysUser对象
    		SysUser sysUser = new SysUser();
    		
    		sysUser.setId(Long.parseLong(id));
    		
    		//检查修改用户名是否为空，为空则修改失败
    		if (!StringUtils.isNullOrEmpty(userName)) {
    			sysUser.setUserName(userName);
    		} else {
    			log.debug("用户名为空");
    			arg1.getOutputStream().write("0".getBytes());
    			return null;
    		}
    		sysUser.setPower(power);
    		//检查修改的性别是否为男或女,不是则修改失败
    		if (sex.equals("男")||sex.equals("女")) {
    			sysUser.setSex(sex);
    		} else {
    			log.debug("性别不是男女");
    			arg1.getOutputStream().write("0".getBytes());
    			return null;
    		}
    		//检查修改的年龄是否为正整数，不是则修改失败
    		if ((!StringUtils.isNullOrEmpty(age))&& age.matches("[0-9]*")) {
    			sysUser.setAge(Integer.parseInt(age));
    		} else {
    			log.debug("年龄不是自然数");
    			arg1.getOutputStream().write("0".getBytes());
    			return null;
    		}
    		
    		sysUser.setPhone(phone);
    		
    		sysUser.setAddress(address);
    		//检查修改密码是否为空，为空则修改失败
    		if (!StringUtils.isNullOrEmpty(password)) {
    			sysUser.setPassword(password);
    		} else {
    			log.debug("密码为空");
    			arg1.getOutputStream().write("0".getBytes());
    			return null;
    		}

    		//调用updateUser方法,成功返回1
    		int ret = sysUserService.updateUser(sysUser);
    		log.debug("更新用户返回结果sysUserService.updateUser(sysUser):"+ret);
    		//响应结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-updateUser异常");
		}
    	
		return null;
	}
    
}
