package com.hainu.hrms.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hainu.hrms.model.Document;


@Repository
public interface DocumentMapper {
	
	/** selectDocumentsByAdmin
	 * 
	 * @return
	 */
	public List<Document> selectDocumentsByUser(Document document);
	
	
	/** selectDocuments
	 * 
	 * @return
	 */
	public List<Document> selectDocuments();
	
	/** deleteDocuments
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteDocuments(List<Integer> list);
	
	
	/** addDocument
	 * 
	 * @param Document
	 * @return
	 */	
	public Integer addDocument(Document document);
	
	/** Document
	 * 
	 * @param Document
	 * @return
	 */
	public Integer updateDocument(Document document);
	
	
}
