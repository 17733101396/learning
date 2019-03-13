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
import com.starnet.musicmanage.model.Advertisement;
import com.starnet.musicmanage.service.IADService;

/*
 * 处理广告操作
 */
@Controller
public class ADController {
	private static final Logger log = Logger.getLogger(ADController.class);
	/*
	 * 自动注入IADService
	 */
	@Autowired
	@Qualifier(value="adServiceImpl")
	private IADService adServiceImpl;
	
	//获取全部广告
    @RequestMapping("/listAllAD.do")
    public ModelAndView listAllAD(HttpServletRequest arg0,HttpServletResponse arg1){
    	try {
    		arg1.setHeader("Content-type", "text/json;charset=utf-8");
    		OutputStream out = arg1.getOutputStream();
    		//调用listAll方法并把结果转化为JSONArray对象
    		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(adServiceImpl.listAllAD());
    		log.debug(jsonArray);
    		//jsonArray对象转化为json字符串
    		byte[] retArr=jsonArray.toJSONString().getBytes("utf-8");
    		//响应字符串给请求页面
    		out.write(retArr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-listAllAD异常");
		}
		return null;
    }
    
    //添加广告
    @RequestMapping("/addAD.do")
    public ModelAndView addAD(HttpServletRequest arg0,HttpServletResponse arg1) {
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
    		//调用.addAD方法添加歌曲,成功返回1
        	String context = arg0.getParameter("context");
    		int ret = adServiceImpl.addAD(context);
    		log.debug("添加广告返回:"+ret);
    		//返回结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-addAD异常");
		}
		return null;
	}
    
    //删除广告
    @RequestMapping("/deleteAD.do")
    public ModelAndView deleteAD(HttpServletRequest arg0,HttpServletResponse arg1) {
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
    		//调用deleteAD方法,成功返回1
    		int ret = adServiceImpl.deleteAD(idList);
    		log.debug("删除歌曲返回:"+ret);
    		//响应结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-deleteAD异常");
		}
		return null;
	}
    
    //更新广告
    @RequestMapping("/updateAD.do")
    public ModelAndView updateAD(HttpServletRequest arg0,HttpServletResponse arg1) {
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
        	String id = arg0.getParameter("id");
        	String context = arg0.getParameter("context");
        	Advertisement advertisement = new Advertisement();
        	advertisement.setId(Integer.parseInt(id));
        	advertisement.setContext(context);
        	int ret = adServiceImpl.updateAD(advertisement);
    		log.debug("更新返回:"+ret);
    		//响应结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-updateAD异常");
		}
		return null;
	}
	
}
