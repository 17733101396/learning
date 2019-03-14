package com.starnet.musicmanage.mapper;

import java.util.List;

import com.starnet.musicmanage.model.SysUser;

public interface SysUserMapper {
	
	/*
	 * 用户登录查询
	 * @param userName 
	 * @param password 
	 * @return List<SysUser> 查询到的所有 sysUser对象的 list
	 */
	public List<SysUser> selectSysUserByUserNameAndPwd(String userName,String password) ;
	
	/*
	 * 查询全部用户
	 * @return List<SysUser> 查询到的所有 sysUser对象的 list
	 */
	public List<SysUser> selectAllSysUser() ;
	
	/*
	 * 新增用户
	 * @param sysUser
	 * @return 插入成功返回1,失败返回0
	 */
    public int insertSysUser(SysUser sysUser);
    
    /*
     * 删除用户
     * @param ids id的List
     * @return 删除成功返回1,失败返回0
     */
    public int deleteSysUser(List<Long> ids) ;
    
    /*
     * 更新用户
     * @param sysUser
     * @return 更新成功返回1,失败返回0
     */
    public int updateSysUser(SysUser sysUser);
    
    
}
