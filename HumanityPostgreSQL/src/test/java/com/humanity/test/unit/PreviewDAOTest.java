package com.humanity.test.unit;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.config.HumanityConfig;
import com.humanity.dao.PreviewDAO;
import com.humanity.model.Preview;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class PreviewDAOTest {

	@Autowired
	PreviewDAO previewDAO;
	
	@Autowired
	HumanityConfig humanConfig;
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getSpecificPreview() {
		
		Preview preview = humanConfig.preview();
		preview.setPreviewChapter(1);
		preview.setPreviewTitle("Updating Preview Title");
		preview.setPreviewSynopsis("Some synopsis about the first chapter in the series.");
		preview.setPreviewReleaseDate(java.sql.Date.valueOf(LocalDate.now().toString()));
		
		assertEquals(1, previewDAO.getSpecificPreview(preview).getPreviewChapter());
		assertEquals("Updating Preview Title", previewDAO.getSpecificPreview(preview).getPreviewTitle());
		
	}
	
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addPreview() {
		
		int currentPreviewsBefore = previewDAO.getAllPreviews().size();
		
		Preview preview = humanConfig.preview();
		preview.setPreviewChapter(2);
		preview.setPreviewTitle("The Eternal Warning");
		preview.setPreviewReleaseDate(java.sql.Date.valueOf(LocalDate.now().toString()));
		
		previewDAO.addPreview(preview);
		
		int currentPreviewsAfter = previewDAO.getAllPreviews().size();
		
		assertEquals(currentPreviewsAfter, currentPreviewsBefore + 1);
		
		previewDAO.deletePreview(preview);
		
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updatePreview() {
		
		Preview preview = humanConfig.preview();
		preview.setPreviewChapter(1);
		preview.setPreviewTitle("Updating Preview Title");
		preview.setPreviewSynopsis("Some synopsis about the first chapter in the series.");
		preview.setPreviewReleaseDate(java.sql.Date.valueOf(LocalDate.now().toString()));
		
		previewDAO.updatePreview(preview);
		
		assertEquals("Updating Preview Title", previewDAO.getSpecificPreview(preview).getPreviewTitle());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getAllPreviews() {
		
		assertEquals(1, previewDAO.getAllPreviews().size());
		
	}
	
	
}
