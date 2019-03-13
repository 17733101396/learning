package com.starnet.musicmanage.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.starnet.musicmanage.model.ClientSong;
import com.starnet.musicmanage.model.Device;
import com.starnet.musicmanage.service.IClientService;


/**
 * 处理客户端请求 Controller
 **/
@Controller
public class ClientController {
	private static final Logger log = Logger.getLogger(ClientController.class);
	
	@Autowired
	@Qualifier(value="clientServiceImpl")
	IClientService clientService;
	//设备上线
	@RequestMapping(value="/clientLogin",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest arg0,HttpServletResponse arg1){
		try {
			arg1.setHeader("Content-type", "text/json;charset=utf-8");
			 OutputStream out = arg1.getOutputStream();
			 String deviceID = arg0.getParameter("deviceID");
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 String time = sdf.format(new Date());//获取当前时间并格式化为yyyy-MM-dd
			 Device device = new Device();
			 device.setDeviceID(deviceID);
			 device.setTime(time);
			 int ret = clientService.login(device);
			 out.write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-login异常");
		}
		 return null;
	 }
	//跳转到client.jsp
	@RequestMapping(value="/toClient",method=RequestMethod.POST)
	public ModelAndView toClient(HttpServletRequest arg0,HttpServletResponse arg1){
		try {
			ModelAndView mav =new ModelAndView();
			mav.setViewName("client.jsp");
			return mav;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-toClient异常");
		}
		return null;
	 }
	
	//设备查询广告
	@RequestMapping(value="/clientSelectAD",method=RequestMethod.POST)
	public ModelAndView selectContext(HttpServletRequest arg0,HttpServletResponse arg1) {
		try {
			 arg1.setHeader("Content-type", "text/json;charset=utf-8");
			 OutputStream out = arg1.getOutputStream();
			 String deviceID = arg0.getParameter("deviceID");
			 //调用service方法并把结果转化为JSONArray对象
			 JSONArray jsonArray = (JSONArray) JSONArray.toJSON(clientService.selectAD(deviceID));
			 //jsonArray对象转化为json字符串
			 byte[] retArr=jsonArray.toJSONString().getBytes("utf-8");
			//响应字符串给请求页面
			out.write(retArr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-selectContext异常");
		}
		return null;
	 }
	
	//设备查询歌曲
	@RequestMapping(value="/clientSelectSong",method=RequestMethod.POST)
	public ModelAndView selectSong(HttpServletRequest arg0,HttpServletResponse arg1){
		try {
			arg1.setHeader("Content-type", "text/json;charset=utf-8");
			 OutputStream out = arg1.getOutputStream();
			 String deviceID = arg0.getParameter("deviceID");
			//调用service方法并把结果转化为JSONArray对象
			 JSONArray jsonArray = (JSONArray) JSONArray.toJSON(clientService.selectSong(deviceID));
			 //jsonArray对象转化为json字符串
			 byte[] retArr=jsonArray.toJSONString().getBytes("utf-8");
			 //响应字符串给请求页面
			 out.write(retArr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-selectSong异常");
		}
		 return null;
	 }
	
	//获取全部歌曲
    @RequestMapping(value="/clientSelectAllSong",method=RequestMethod.POST)
    public ModelAndView clientSelectAllSong(HttpServletRequest arg0,HttpServletResponse arg1){
    	try {
    		arg1.setHeader("Content-type", "text/json;charset=utf-8");
    		OutputStream out = arg1.getOutputStream();
    		String deviceID = arg0.getParameter("deviceID");
    		//调用service方法并把结果转化为JSONArray对象
    		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(clientService.selectAllSong(deviceID));
    		//jsonArray对象转化为json字符串
    		byte[] retArr=jsonArray.toJSONString().getBytes("utf-8");
    		//响应字符串给请求页面
    		out.write(retArr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-clientselectAllSong异常");
		}
		return null;
    }
	
    //获取全部歌曲
    @RequestMapping(value="/collectionManage",method=RequestMethod.POST)
    public ModelAndView collectionManage(HttpServletRequest arg0,HttpServletResponse arg1){
    	try {
    		arg1.setHeader("Content-type", "text/json;charset=utf-8");
    		OutputStream out = arg1.getOutputStream();
    		String deviceID = arg0.getParameter("deviceID");
    		int songID = Integer.parseInt(arg0.getParameter("songID"));
    		int col = Integer.parseInt(arg0.getParameter("collection"));
    		log.debug(deviceID+songID+col);
    		ClientSong clientSong = new ClientSong();
    		clientSong.setDeviceID(deviceID);
    		clientSong.setSongID(songID);
    		clientSong.setCollection(col);
    		int ret  = clientService.collectionManage(clientSong);
    		out.write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-collectionManage异常");
		}
		return null;
    }
    
    @RequestMapping(value="/cancleCollection",method=RequestMethod.POST)
    public ModelAndView cancleCollection(HttpServletRequest arg0,HttpServletResponse arg1){
    	try {
    		arg1.setHeader("Content-type", "text/json;charset=utf-8");
    		OutputStream out = arg1.getOutputStream();
    		String deviceID = arg0.getParameter("deviceID");
    		String []songList = arg0.getParameterValues("songList[]");
    		int ret=clientService.cancleCollection(deviceID, songList);
    		out.write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-cancleCollection异常");
		}
		return null;
    }
    
    
}
