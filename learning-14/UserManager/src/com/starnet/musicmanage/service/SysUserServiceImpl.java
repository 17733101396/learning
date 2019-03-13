package com.starnet.musicmanage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starnet.musicmanage.mapper.SysUserMapper;
import com.starnet.musicmanage.model.SysUser;

/*
 * 将当前类注释为一个 Spring 的 bean
 */
@Service("sysUserServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements ISysUserService {
	
	private static final Logger log = Logger.getLogger(SysUserServiceImpl.class);
	
	/**
     * 自动注入 SysUserMapper
     **/
	@Autowired
	SysUserMapper sysUserMapper;
	// 下面是  ISysUserService接口所有方法的具体实现
	public int addSysUser(SysUser sysUser) {
		try {
			return sysUserMapper.insertSysUser(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("sysUserMapper.insertSysUser(sysUser)出现异常");
		}
		return 0;	
	}
	public List<SysUser> listAll() {
		try {
			return sysUserMapper.selectAllSysUser();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("sysUserMapper.selectAllSysUser()出现异常");
		}
		return null;
	}
	
	public int login(String userName,String password){
		try {
			List<SysUser> list = sysUserMapper.selectSysUserByUserNameAndPwd(userName, password);
			String power = null;
			for (SysUser sysUser : list) {
				power = sysUser.getPower();
			}
			if (power.equals("超级管理员")) {
				log.debug("power==超级管理员");
				return 1;
			}else if (power.equals("一般管理员")) {
				log.debug("power==一般管理员");
				return 2;
			}else {
				log.debug("不是管理员");
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("sysUserMapper.selectSysUserByUserNameAndPwd(userName, password)出现异常");
		}
		return 0;
		
	}
	
	public int deleteUsers(List<Long> ids) {
		try {
			//如果删除的id都能在数据库中查找到，则删除成功，返回1，否则返回0
			if (sysUserMapper.deleteSysUser(ids)==ids.size()) {
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("sysUserMapper.deleteSysUser(ids)出现异常");
		}
		return 0;
	}
	
	public int updateUser(SysUser sysUser) {
		try {
			return sysUserMapper.updateSysUser(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("sysUserMapper.updateSysUser(sysUser)出现异常");
		}
		return 0;	
	}
}
