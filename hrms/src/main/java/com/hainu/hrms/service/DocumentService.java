package com.hainu.hrms.service;

import java.util.List;

import com.hainu.hrms.model.Document;

public interface DocumentService {
	/** selectFilesByAdmin
	 * 
	 * @return
	 */
	public List<Document> selectDocumentsByUser(Document document);
	
	
	/** selectFiles
	 * 
	 * @return
	 */
	public List<Document> selectDocuments();
	
	/** deleteFiles
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteDocuments(List<Integer> list);
	
	
	/** addFile
	 * 
	 * @param Document
	 * @return
	 */	
	public Integer addDocument(Document document);
	
	/** File
	 * 
	 * @param Document
	 * @return
	 */
	public Integer updateDocument(Document document);
}
