package com.hainu.hrms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.hainu.hrms.model.Post;
import com.hainu.hrms.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	private static final Logger log = Logger.getLogger(PostController.class);
	@Autowired
	@Qualifier("postService")
	PostService postService;

	
	@RequestMapping(value="/selectPostsByUser",method=RequestMethod.GET)
	public String selectPostsByUser(@RequestParam("admin_id") Integer admin_id,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			log.debug(admin_id);
			Post post=new Post();
			post.setAdmin_id(admin_id);
			List<Post> list = postService.selectPostsByUser(post);
			String PostResult=JSON.toJSONString(list);
			response.getWriter().write(PostResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/selectPosts",method=RequestMethod.GET)
	public String selectPosts(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			List<Post> list = postService.selectPosts();
			String PostResult=JSON.toJSONString(list);
			response.getWriter().write(PostResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/deletePosts",method=RequestMethod.POST)
	public String deletePosts(@RequestBody Map<String,List<Integer>> map,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			if (map.get("id")==null) {
				log.debug("空");
				return null;
			}
    	   	log.debug(map);
			int res=postService.deletePosts(map.get("id"));		
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/addPost",method=RequestMethod.POST)
	public String addPost(@RequestBody Post post,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=postService.addPost(post);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/updatePost",method=RequestMethod.POST)
	public String updatePost(@RequestBody Post post,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=postService.updatePost(post);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	
}
