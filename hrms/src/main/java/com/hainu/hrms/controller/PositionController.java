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

import com.alibaba.fastjson.JSON;
import com.hainu.hrms.model.Position;
import com.hainu.hrms.service.PositionService;


@Controller
@RequestMapping("/position")
public class PositionController {
	private static final Logger log = Logger.getLogger(PositionController.class);
	@Autowired
	@Qualifier("positionService")
	PositionService positionService;
	
	@RequestMapping(value="/selectPositions",method=RequestMethod.GET)
	public String selectPositions(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			List<Position> list = positionService.selectPositions();
			String PositionResult=JSON.toJSONString(list);
			response.getWriter().write(PositionResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/deletePositions",method=RequestMethod.POST)
	public String deletePositions(@RequestBody Map<String,List<Integer>> map,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			if (map.get("id")==null) {
				log.debug("空");
				return null;
			}
    	   	log.debug(map);
			int res=positionService.deletePositions(map.get("id"));		
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/addPosition",method=RequestMethod.POST)
	public String addPosition(@RequestBody Position position,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=positionService.addPosition(position);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/updatePosition",method=RequestMethod.POST)
	public String updatePosition(@RequestBody Position position,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			int res=positionService.updatePosition(position);
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	
}
