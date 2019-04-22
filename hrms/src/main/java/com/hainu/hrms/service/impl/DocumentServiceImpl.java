package com.hainu.hrms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hainu.hrms.mapper.DocumentMapper;
import com.hainu.hrms.model.Document;
import com.hainu.hrms.service.DocumentService;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	DocumentMapper documentMapper;
	@Override
	public List<Document> selectDocumentsByUser(Document document) {
		// TODO Auto-generated method stub
		return documentMapper.selectDocumentsByUser(document);
	}

	@Override
	public List<Document> selectDocuments() {
		// TODO Auto-generated method stub
		return documentMapper.selectDocuments();
	}

	@Override
	public Integer deleteDocuments(List<Integer> list) {
		// TODO Auto-generated method stub
		return documentMapper.deleteDocuments(list);
	}

	@Override
	public Integer addDocument(Document Document) {
		// TODO Auto-generated method stub
		return documentMapper.addDocument(Document);
	}

	@Override
	public Integer updateDocument(Document Document) {
		// TODO Auto-generated method stub
		return documentMapper.updateDocument(Document);
	}

}
