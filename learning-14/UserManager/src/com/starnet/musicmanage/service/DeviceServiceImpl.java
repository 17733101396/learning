package com.starnet.musicmanage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starnet.musicmanage.mapper.DeviceMapper;
import com.starnet.musicmanage.model.Device;

/*
 * 将当前类注释为一个 Spring 的 bean
 */
@Service("deviceServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl implements IDeviceService {
	private static final Logger log = Logger.getLogger(DeviceServiceImpl.class);
	@Autowired
	DeviceMapper deviceMapper;
	public int addDevice(Device device) {
		// TODO Auto-generated method stub
		try {
			return deviceMapper.insertDevice(device);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("addDevice异常");
		}
		return 0;
	}

	public List<Device> listAll(int deviceType) {
		// TODO Auto-generated method stub
		try {
			return deviceMapper.selectAllDevice(deviceType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("listAll异常");
		}
		return null;
	}
	
	public int deleteDevice(List<Integer> ids) {
		// TODO Auto-generated method stub
		try {
			if (deviceMapper.deleteDevice(ids)==ids.size()) {
				return 1;
			}
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("deleteDevice异常");
		}
		return 0;
	}
	
	public int updateDevice(Device device) {
		// TODO Auto-generated method stub
		try {
			return deviceMapper.updateDevice(device);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("updateDevice异常");
		}
		return 0;
	}

}
