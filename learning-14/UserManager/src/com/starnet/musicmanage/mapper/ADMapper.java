package com.starnet.musicmanage.mapper;

import java.util.List;

import com.starnet.musicmanage.model.Advertisement;

public interface ADMapper {
	
	/*
	 * 根据id查询广告内容
	 * @param adList广告id列表
	 * @return 返回Advertisement的list 
	 */
	public List<Advertisement> selectADById(List<Integer> list); 
	
	/*
	 * 查询全部广告
	 * @return List<Advertisement> 查询到的所有Advertisement对象的 list
	 */
	public List<Advertisement> selectAllAD() ;
	
	/*
	 * 添加广告
	 * @param context
	 * @return 插入成功返回1,失败返回0
	 */
    public int insertAD(String context);
    
    /*
     * 删除广告
     * @param ids id的List
     * @return 删除成功返回1,失败返回0
     */
    public int deleteAD(List<Integer> ids) ;
    
    /*
     * 更新广告内容
     * @param Advertisement
     * @return 更新成功返回1,失败返回0
     */
    public int updateAD(Advertisement advertisement);
} 
