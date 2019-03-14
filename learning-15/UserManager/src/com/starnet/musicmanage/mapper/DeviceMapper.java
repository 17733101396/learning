package com.starnet.musicmanage.mapper;

import java.util.List;

import com.starnet.musicmanage.model.Device;

public interface DeviceMapper {
	
	/*
	 * 更新设备在线状态为上线及时间
	 * @param device
	 * @return 数据更新成功返回1 失败返回0
	 */
	public int loginUpdateType(Device device);
	
	/*
	 * 更新设备在线状态为下线及时间
	 * @param device
	 * @return 数据更新成功返回1,失败返回0
	 */
	public int logoutUpdateType(Device device);
	
	/*
	 * 查询设备广告
	 * @param deviceID 设备ID
	 * @return String 广告id列表
	 */
	public String selectAdListByDeviceID(String deviceID);
	
	/*
	 * 查询设备歌曲
	 * @param deviceID
	 * @return String 歌曲id列表
	 */
	public String selectSongListByDeviceID(String deviceID);
	
	/*
	 * 查询全部设备
	 * @param deviceType
	 * @return List<Device> 查询到的所有 device对象的 list
	 */
	public List<Device> selectAllDevice(int deviceType) ;
	
	/*
	 * 新增设备
	 * @param device
	 * @return 插入成功返回1,失败返回0
	 */
    public int insertDevice(Device device);
    
    /*
     * 删除设备
     * @param ids id的List
     * @return 删除成功返回1,失败返回0
     */
    public int deleteDevice(List<Integer> ids) ;
    
    /*
     * 更新用户
     * @param sysUser
     * @return 更新成功返回1,失败返回0
     */
    public int updateDevice(Device device);
    
    /*
     * 更新设备歌曲列表
     * @param device
     * @return 成功返回1 失败返回0
     */
    public int updateDeviceSongList(Device device);
}
