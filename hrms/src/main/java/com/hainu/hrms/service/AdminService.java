package com.hainu.hrms.service;

import java.util.List;

import com.hainu.hrms.model.Admin;


public interface AdminService {

	/** login
     * 
     * @param admin
     * @return Admin
     */
    public Admin login(Admin admin);

    /** selectAdmins
     * @param admin
     * @return Admin的列表
     */
    public List<Admin> selectAdmins();
    
    /** deleteAdmins
     * 
     * @param list
     * @return Integer
     */
    public Integer deleteAdmins(List<Integer> list);

    /** addAdmin
     * 
     * @param admin
     * @return Integer
     */
    public Integer addAdmin(Admin admin);

    /** updateAdmin
     * 
     * @param admin
     * @return Integer
     */
    public Integer updateAdmin(Admin admin);
}