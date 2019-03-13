package com.starnet.musicmanage.service;

import java.util.List;

import com.starnet.musicmanage.model.SysUser;

public interface ISysUserService {
	
	/*
	 * 添加用户
	 * @param sysUser
	 * @return 添加成功返回1,失败返回0
	 */
	public int addSysUser(SysUser sysUser);
	
	/*
	 * 查询所有用户
	 * @return 查询到的所有 sysUser对象的 list
	 */
	public List<SysUser> listAll();
	
	/*
	 * 用户登录
	 * @param userName
	 * @param password 
	 * @return 登录成功:超级管理员返回1,一般管理员返回2,失败返回0
	 */
	public int login(String userName,String password);
	
	/*
	 * 删除用户
	 * @param ids
	 * @return 删除成功返回1,失败返回0
	 */
	public int deleteUsers(List<Long> ids);
	
	/*
	 * 更新用户
	 * @param sysUser
	 * @return 更新成功返回1,失败返回0
	 */
	public int updateUser(SysUser sysUser);
}
