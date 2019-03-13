package com.starnet.musicmanage.service;

import java.util.List;

import com.starnet.musicmanage.model.Advertisement;

public interface IADService {
	/*
	 * 查询所有广告
	 * @return 查询到的所有Advertisement对象的 list
	 */
	public List<Advertisement> listAllAD();
	
	/*
	 * 添加广告
	 * @param context 广告内容
	 * @return 添加成功返回1,失返回0
	 */
	public int addAD(String context);
	
	/*
	 * 更新广告
	 * @param advertisement
	 * @return 更新成功返回1,失返回0
	 */
	public int updateAD(Advertisement advertisement);
	
	/*
	 * 删除广告
	 * @param ids
	 * @return 删除成功返回1,失败返回0
	 */
	public int deleteAD(List<Integer> ids);
	
}
