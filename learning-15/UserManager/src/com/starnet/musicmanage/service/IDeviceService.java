package com.starnet.musicmanage.service;

import java.util.List;

import com.starnet.musicmanage.model.Device;

public interface IDeviceService {
	/*
	 * 添加设备
	 * @param device
	 * @return 添加成功返回1,失败返回0
	 */
	public int addDevice(Device device);
	
	/*
	 * 查询所有设备
	 * @param deviceType
	 * @return 查询到的所有 device对象的 list
	 */
	public List<Device> listAll(int deviceType);
	
	/*
	 * 删除设备
	 * @param ids
	 * @return 删除成功返回1,失败返回0
	 */
	public int deleteDevice(List<Integer> ids);
	
	/*
	 * 更新设备
	 * @param device
	 * @return 更新成功返回1,失败返回0
	 */
	public int updateDevice(Device device);
}
