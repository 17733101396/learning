package com.hainu.hrms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hainu.hrms.mapper.AdminMapper;
import com.hainu.hrms.model.Admin;
import com.hainu.hrms.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminMapper adminMapper;
	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return adminMapper.login(admin);
	}

	@Override
	public List<Admin> selectAdmins() {
		// TODO Auto-generated method stub
		return adminMapper.selectAdmins();
	}

	@Override
	public Integer deleteAdmins(List<Integer> list) {
		// TODO Auto-generated method stub
		return adminMapper.deleteAdmins(list);
	}

	@Override
	public Integer addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminMapper.addAdmin(admin);
	}

	@Override
	public Integer updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminMapper.updateAdmin(admin);
	}

}
