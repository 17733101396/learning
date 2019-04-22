package com.hainu.hrms.controller;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.hainu.hrms.model.Document;
import com.hainu.hrms.service.DocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {
	private static final Logger log = Logger.getLogger(DocumentController.class);
	@Autowired
	@Qualifier("documentService")
	DocumentService documentService;

	
	@RequestMapping(value="/selectDocumentsByUser",method=RequestMethod.GET)
	public String selectDocumentsByAdmin(@RequestParam("admin_id") Integer admin_id,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			Document document=new Document();
			document.setAdmin_id(admin_id);
			List<Document> list = documentService.selectDocumentsByUser(document);
			String FileResult=JSON.toJSONString(list);
			response.getWriter().write(FileResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/selectDocuments",method=RequestMethod.GET)
	public String selectDocuments(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
			List<Document> list = documentService.selectDocuments();
			String FileResult=JSON.toJSONString(list);
			response.getWriter().write(FileResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/deleteDocuments",method=RequestMethod.POST)
	public String deleteDocuments(@RequestBody List<Document> list,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "application/json;charset=utf-8");
    		//检查数组是否为空
    		if (list==null) {
    			log.debug("list为空");
    			return null;
    		}
    		List<Integer> ids=new ArrayList<>();
    		List<String> names=new ArrayList<>();
    		for (Document document : list) {
				ids.add(document.getId());
				names.add(document.getName());
			}	
    		for (String name : names) {
    			 File file = new File("D:/file/"+name);
    			 if (file.exists() && file.isFile()) {
    				 file.delete();
    			 }
    		            
			}
			int res=documentService.deleteDocuments(ids);		
			response.getWriter().write(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/uploadDocument",method=RequestMethod.POST)
	public String uploadDocument(@RequestParam("admin_id") Integer admin_id,@RequestParam("create_date") String create_date,@RequestParam("file")CommonsMultipartFile[] partFiles,HttpServletRequest request,HttpServletResponse response) {
		try {
			 response.setHeader("Content-type", "text/json;charset=utf-8");
			 log.debug(admin_id+"----"+create_date);
			 for (CommonsMultipartFile partFile : partFiles) {
				 String filename = partFile.getOriginalFilename();
				 InputStream is = partFile.getInputStream();
				 log.debug("文件的名字:"+filename);	 
			     //创建要上传的路径        
				 File fdir=new File("D:/file");
				 if (!fdir.exists()) {  
					 fdir.mkdirs(); 
			     }
			     //文件上传到路径下            
				 FileUtils.copyInputStreamToFile(is, new File(fdir,filename));
				 Document document=new Document();
				 document.setName(filename);
				 document.setAdmin_id(admin_id);
				 document.setCreate_date(new SimpleDateFormat("yyyy-MM-dd").parse(create_date));
				 int res=documentService.addDocument(document);
				 response.getWriter().write(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("uploadFile异常");
		}
		return null;
	}
	
	@RequestMapping("/downloadDocument")
	 public String downloadDocument(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 try {
			 String filename =URLDecoder.decode(request.getParameter("name"),"UTF-8"); 
			 log.debug(filename);
			 response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
		     File file = new File("D:/file"+"/"+filename); 
		     String length = String.valueOf(file.length());
		     response.addHeader("Content-Length",length);//设置下载文件大小
		     if (!file.exists()) { 
		    	 log.debug("文件不存在");
	              return null;
	          }
			 FileUtils.copyFile(file, response.getOutputStream()); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("下载失败");
		}
		 return null;		 
	 }
	
	
	
	
}
