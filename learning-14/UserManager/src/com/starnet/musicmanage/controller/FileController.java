package com.starnet.musicmanage.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/*
 * 处理文件上传下载请求
 */
@Controller
public class FileController {
	 private static final Logger log = Logger.getLogger(FileController.class);
	 
	 @RequestMapping("/uploadFile.do")
	 public ModelAndView uploadFile(@RequestParam("uploadFile")CommonsMultipartFile partFile,HttpServletRequest arg0,HttpServletResponse arg1) throws Exception{
		OutputStream out = arg1.getOutputStream();
		String filename = partFile.getOriginalFilename();
		log.debug("文件的名字:"+filename);
		out.write(String.valueOf(1).getBytes());
		return null;
	 }
	 
	 @RequestMapping("/downloadFile.do")
	 public ModelAndView downloadFile(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception{
			return null;		 
	 }
}
