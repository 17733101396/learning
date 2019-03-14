package com.starnet.musicmanage.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.starnet.musicmanage.model.Device;
import com.starnet.musicmanage.service.IDeviceService;

/**
 * 处理设备管理请求 Controller
 **/
@Controller
public class DeviceController {
	
	private static final Logger log = Logger.getLogger(DeviceController.class);
	
	/**
     * 自动注入IDeviceService
     **/
	@Autowired
	@Qualifier(value="deviceServiceImpl")
    private IDeviceService deviceService;
	
	//获取全部设备信息(andriod或linux)
    @RequestMapping("/listAllDevice")
    public ModelAndView listAllDevice(HttpServletRequest arg0,HttpServletResponse arg1) {
    	try {
    		arg1.setHeader("Content-type", "text/json;charset=utf-8");
        	int deviceType = Integer.parseInt(arg0.getParameter("paramDeviceType"));
        	log.debug("deviceType:"+deviceType);
    		OutputStream out = arg1.getOutputStream();
    		//调用listAll方法并把结果转化为JSONArray对象
    		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(this.deviceService.listAll(deviceType));
    		//jsonArray对象转化为json字符串
    		byte[] retArr=jsonArray.toJSONString().getBytes("utf-8");
    		//响应字符串给请求页面
    		out.write(retArr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-listAllDevice异常");
		}
		return null;
    }
    
    //添加设备
    @RequestMapping("/addDevice")
    public ModelAndView addDevice(HttpServletRequest arg0,HttpServletResponse arg1){
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
        	String deviceID = arg0.getParameter("deviceID");
        	String deviceName= arg0.getParameter("deviceName");
        	String deviceType= arg0.getParameter("deviceType");
        	String type= arg0.getParameter("type");
        	String time=arg0.getParameter("time");
        	String adList= arg0.getParameter("adList");
        	String songList= arg0.getParameter("songList");
        	Device device = new Device();
        	device.setDeviceID(deviceID);
        	device.setDeviceName(deviceName);
        	device.setDeviceType(Integer.parseInt(deviceType));
        	device.setType(Integer.parseInt(type));
        	device.setTime(time);
        	device.setAdList(adList);
        	device.setSongList(songList);
    		//调用.addDevice方法添加设备,成功返回1
    		int ret = deviceService.addDevice(device);
    		log.debug("插入用户返回结果deviceService.addDevice(device):"+ret);
    		//返回结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-addDevice异常");
		}
    	
		return null;
	}
    
    //删除设备
    @RequestMapping("/deleteDevice")
    public ModelAndView deleteDevice(HttpServletRequest arg0,HttpServletResponse arg1) {
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
    		//获取请求id数组
    		String[] ids=arg0.getParameterValues("ids[]");
    		//检查数组是否为空
    		if (ids==null) {
    			log.debug("ids为空");
    			return null;
    		}
    	    //创建Integer型的List,并把ids数组转化后添加进去
    		List<Integer> idList = new ArrayList<Integer>();
    		for (String id : ids) {
    			idList.add(Integer.valueOf(id));
    		}	
    		//调用deleteDevice方法,成功返回1
    		int ret = deviceService.deleteDevice(idList);
    		log.debug("删除设备返回:"+ret);
    		//响应结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-deleteDevice异常");
		}
		return null;
	}
    
    //更新设备
    @RequestMapping("/updateDevice")
    public ModelAndView updateDevice(HttpServletRequest arg0,HttpServletResponse arg1) {
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
        	String id = arg0.getParameter("id");
        	String deviceID = arg0.getParameter("deviceID");
        	String deviceName= arg0.getParameter("deviceName");
        	String deviceType= arg0.getParameter("deviceType");
        	String type= arg0.getParameter("type");
        	String time= arg0.getParameter("time");
        	String adList= arg0.getParameter("adList");
        	String songList= arg0.getParameter("songList");
        	Device device = new Device();
        	device.setId(Integer.parseInt(id));
        	device.setDeviceID(deviceID);
        	device.setDeviceName(deviceName);
        	device.setDeviceType(Integer.parseInt(deviceType));
        	device.setType(Integer.parseInt(type));
        	device.setTime(time);
        	device.setAdList(adList);
        	device.setSongList(songList);
    		//调用updateDevice方法,成功返回1
    		int ret = deviceService.updateDevice(device);
    		log.debug("更新设备返回结果deviceService.updateDevice(device):"+ret);
    		//响应结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-updateDevice异常");
		}
		return null;
	}
	
	
}
