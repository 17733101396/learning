package com.starnet.musicmanage.controller;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
	 
	 @RequestMapping("/uploadFile")
	 public ModelAndView uploadFile(@RequestParam("uploadFile")CommonsMultipartFile partFile,HttpServletRequest arg0,HttpServletResponse arg1) throws Exception{
		OutputStream out = arg1.getOutputStream();
		String filename = partFile.getOriginalFilename();
		InputStream is = partFile.getInputStream();
		log.debug("文件的名字:"+filename);
		try{   
            //创建要上传的路径        
			File fdir = new File("D:/file");      
			if (!fdir.exists()) { 
              fdir.mkdirs(); 
              }
            //文件上传到路径下            
			FileUtils.copyInputStreamToFile(is, new File(fdir,filename));              
			//coding                        
 		} catch (Exception e) {   
 			log.debug("upload error");
 		}  		
		out.write(String.valueOf(1).getBytes());
		return null;
	 }
	 
	 @RequestMapping("/downloadFile")
	 public ModelAndView downloadFile(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception{
		 try {
			 String filename =URLDecoder.decode(arg0.getParameter("filename"),"UTF-8"); 
			 log.debug(filename);
			 arg1.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
		     File fdir = new File("D:/file"+"/"+filename); 
		     if (!fdir.exists()) { 
		    	 log.debug("文件不存在");
	              return null;
	          }
			 FileUtils.copyFile(fdir, arg1.getOutputStream()); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("下载失败");
		}
		 return null;		 
	 }
}
