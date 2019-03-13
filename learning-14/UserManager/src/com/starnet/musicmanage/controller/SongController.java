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
import com.starnet.musicmanage.model.Song;
import com.starnet.musicmanage.service.ISongService;

/*
 * 处理歌曲操作请求
 */
@Controller
public class SongController {
	private static final Logger log = Logger.getLogger(SongController.class);
	/*
	 * 自动注入ISongService
	 */
	@Autowired
	@Qualifier(value="songServiceImpl")
	private ISongService songServiceImpl;
	
	//获取全部歌曲
    @RequestMapping("/listAllSong.do")
    public ModelAndView listAllSong(HttpServletRequest arg0,HttpServletResponse arg1){
    	try {
    		arg1.setHeader("Content-type", "text/json;charset=utf-8");
    		OutputStream out = arg1.getOutputStream();
    		//调用listAll方法并把结果转化为JSONArray对象
    		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(songServiceImpl.listAllSong());
    		//jsonArray对象转化为json字符串
    		byte[] retArr=jsonArray.toJSONString().getBytes("utf-8");
    		//响应字符串给请求页面
    		out.write(retArr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-listAllSong异常");
		}
		return null;
    }
    
    //添加歌曲
    @RequestMapping("/addSong.do")
    public ModelAndView addSong(HttpServletRequest arg0,HttpServletResponse arg1) {
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
        	String songName= arg0.getParameter("songName");
        	String introduction = arg0.getParameter("introduction");
        	String singer = arg0.getParameter("singer");
        	Song song = new Song();
        	song.setSongName(songName);
        	song.setIntroduction(introduction);
        	song.setSinger(singer);
    		//调用.addSong方法添加歌曲,成功返回1
    		int ret = songServiceImpl.addSong(song);
    		log.debug("添加歌曲返回:"+ret);
    		//返回结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-addSong异常");
		}
    	
		return null;
	}
    
    //删除歌曲
    @RequestMapping("/deleteSong.do")
    public ModelAndView deleteSong(HttpServletRequest arg0,HttpServletResponse arg1) {
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
    		//调用deleteSong方法,成功返回1
    		int ret = songServiceImpl.deleteSong(idList);
    		log.debug("删除歌曲返回:"+ret);
    		//响应结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-deleteSong异常");
		}
    	
		return null;
	}
    
    //更新歌曲
    @RequestMapping("/updateSong.do")
    public ModelAndView updateSong(HttpServletRequest arg0,HttpServletResponse arg1){
    	try {
    		arg1.setHeader("Content-type", "text/html;charset=utf-8");
        	String songID = arg0.getParameter("songID");
        	String songName = arg0.getParameter("songName");
        	String introduction = arg0.getParameter("introduction");
        	String singer = arg0.getParameter("singer");
        	Song song = new Song();
        	song.setSongID(Integer.parseInt(songID));
        	song.setSongName(songName);
        	song.setIntroduction(introduction);
        	song.setSinger(singer);
        	int ret = songServiceImpl.updateSong(song);
    		log.debug("更新返回:");
    		//响应结果给请求页面
    		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("controller-updateSong异常");
		}
    	
		return null;
	}
	
	
}
