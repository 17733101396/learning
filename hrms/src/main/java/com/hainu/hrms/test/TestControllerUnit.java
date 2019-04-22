package com.hainu.hrms.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hainu.hrms.mapper.AdminMapper;
import com.hainu.hrms.utils.TokenUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@WebAppConfiguration
public class TestControllerUnit {
	
	private static final Logger log = Logger.getLogger(TestControllerUnit.class);
	
	@Autowired
	AdminMapper adminMapper;
	@Autowired
	TokenUtil tokenUtil;
	
	MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }
    
	
	/*
	 * 测试LoginController的login接口
	 */
    @Test
	public void testLogin() {
		String username="superadmin";
		String password="123456";
		try {
			String map = mockMvc.perform(MockMvcRequestBuilders
					.get("/login")
					.contentType(MediaType.APPLICATION_JSON)
					.param("username",username)
					.param("password", password)
					).andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
			log.debug(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
