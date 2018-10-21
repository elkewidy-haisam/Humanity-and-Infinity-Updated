package com.humanity.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.dao.OrderHistoryDAO;
import com.humanity.dao.PreviewDAO;
import com.humanity.model.Preview;

@Service
public class PreviewService {
	
	private static final Logger log = Logger.getLogger(PreviewService.class);
	private PreviewDAO previewDAO;
	
	@Autowired
	public void setPreviewDAO(PreviewDAO previewDAO) {
		
		this.previewDAO = previewDAO;
		
	}
	
	public List<Preview> getAllPreviews() {
		
		return previewDAO.getAllPreviews();
		
	}
	
	public Preview getSpecificPreview(Preview preview) {
		
		return previewDAO.getSpecificPreview(preview);
		
	}
	
	public void addPreview(Preview preview) {
		
		previewDAO.addPreview(preview);
		
	}
	
	public Preview updatePreview(Preview preview) {
		
		previewDAO.updatePreview(preview);
		return preview;
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void deletePreview(Preview preview) {
		
		previewDAO.deletePreview(preview);
		
	}

}
