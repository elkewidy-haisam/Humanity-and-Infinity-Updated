package com.humanity.test.integration;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.config.HumanityConfig;
import com.humanity.model.Preview;
import com.humanity.services.PreviewService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class PreviewServiceTest {
	
	@Autowired
	PreviewService previewService;
	
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
		
		assertEquals(1, previewService.getSpecificPreview(preview).getPreviewChapter());
		assertEquals("Updating Preview Title", previewService.getSpecificPreview(preview).getPreviewTitle());
		
	}
	
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addPreview() {
		
		int currentPreviewsBefore = previewService.getAllPreviews().size();
		
		Preview preview = humanConfig.preview();
		preview.setPreviewChapter(2);
		preview.setPreviewTitle("The Eternal Warning");
		preview.setPreviewReleaseDate(java.sql.Date.valueOf(LocalDate.now().toString()));
		
		previewService.addPreview(preview);
		
		int currentPreviewsAfter = previewService.getAllPreviews().size();
		
		assertEquals(currentPreviewsAfter, currentPreviewsBefore + 1);
		
		previewService.deletePreview(preview);
		
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updatePreview() {
		
		Preview preview = humanConfig.preview();
		preview.setPreviewChapter(1);
		preview.setPreviewTitle("Updating Preview Title");
		preview.setPreviewSynopsis("Some synopsis about the first chapter in the series.");
		preview.setPreviewReleaseDate(java.sql.Date.valueOf(LocalDate.now().toString()));
		
		previewService.updatePreview(preview);
		
		assertEquals("Updating Preview Title", previewService.getSpecificPreview(preview).getPreviewTitle());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getAllPreviews() {
		
		assertEquals(1, previewService.getAllPreviews().size());
		
	}

}
