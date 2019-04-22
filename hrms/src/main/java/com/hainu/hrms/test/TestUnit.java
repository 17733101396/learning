package com.hainu.hrms.test;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.hainu.hrms.mapper.AdminMapper;
import com.hainu.hrms.mapper.EmployeeMapper;
import com.hainu.hrms.model.Admin;
import com.hainu.hrms.model.Employee;
import com.hainu.hrms.utils.TokenUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
public class TestUnit {
	private static final Logger log = Logger.getLogger(TestUnit.class);
	
	@Autowired
	AdminMapper adminMapper;
	@Autowired
	TokenUtil tokenUtil;
	@Autowired
	EmployeeMapper employeeMapper;
	/*
	 * 测试数据库登录查询
	 */
	//@Test
	public void login() {
		Admin admin = new Admin();
		admin.setUsername("superadmin");
		admin.setPassword("123456");
		Admin admin2=adminMapper.login(admin);
		if (StringUtils.isEmpty(admin2)) {
			log.debug("查无此人");
		} else {
			String json = JSON.toJSONString(admin2);
			log.debug(json);
			try {
				byte[] retByte=json.getBytes("utf-8");
				String ret = new String(retByte, "utf-8");
				log.debug("retByte:"+retByte+"------------"+"ret:"+ret);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * 测试token的生成与验证
	 */
	//@Test
	public void token() {
		try {
			//私钥及加密算法
			Algorithm algorithm = Algorithm.HMAC256("secret");
			//过期时间   如果设为new Date(System.currentTimeMillis())即立刻过期则下面的token校验不能通过
			Date date=new Date(System.currentTimeMillis()+10*60*1000);
			String token = JWT.create()
					.withClaim("username", "superadmin")
					.withExpiresAt(date)
				    .sign(algorithm);
			log.debug(token);
			//校验私钥是否正确的同时校验是否过期
			log.debug(JWT.require(algorithm).build().verify(token));
			//解码取出username
			log.debug("username:"+JWT.decode(token).getClaim("username").asString());
		} catch (JWTCreationException | IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 测试TokenUtil工具类的使用
	 */
	//@Test
	public void testTokenUtil() {
		String username="admin";
		String password="123456";
		String role_name="superadmin";
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setRole_name(role_name);
		String token=tokenUtil.createToken(admin);
		log.debug(JWT.decode(token).getClaim("password").asString());
		log.debug(tokenUtil.verifyToken(token));
	}
	
	@Test
	public void testEmployee() {
		try {
			JSONArray jsonArray=(JSONArray) JSONArray.toJSON(employeeMapper.selectEmployees());
			log.debug(jsonArray);
			Employee employee=new Employee();
			employee.setName("amy");
			employee.setSex("male");
			employee.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1996-10-30"));
			employee.setPhone("1773310196");
			employee.setEmail("17733101396@163.com");
			employee.setAddress("海南大学");
			employee.setDept_id(1);
			employee.setPos_id(1);
			log.debug(employeeMapper.addEmployee(employee));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
