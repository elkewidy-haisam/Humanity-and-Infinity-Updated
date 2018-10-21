package com.humanity.test.mockControllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.humanity.config.HumanityConfig;
import com.humanity.controllers.PreviewController;
import com.humanity.model.Comic;
import com.humanity.model.Preview;
import com.humanity.services.PreviewService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
@WebAppConfiguration
public class PreviewControllerTest {
	
	
	@InjectMocks
	PreviewController previewController;
	
	@Mock
	PreviewService previewServiceMock;
	
	
	Preview preview1, preview2, preview3, preview4, preview5;
	
	List<Preview> previews = new ArrayList<Preview>();
	
	@Before
	public void setup() {
		
		preview1 = new Preview();
		preview1.setPreviewChapter(1);
		preview1.setPreviewSynopsis("Mock Preview Synopsis 1");
		preview1.setPreviewTitle("Mock Preview Title 1");
		
		preview2 = new Preview();
		preview2.setPreviewChapter(2);
		preview2.setPreviewSynopsis("Mock Preview Synopsis 2");
		preview2.setPreviewTitle("Mock Preview Title 2");
		
		preview3 = new Preview();
		preview3.setPreviewChapter(3);
		preview3.setPreviewSynopsis("Mock Preview Synopsis 3");
		preview3.setPreviewTitle("Mock Preview Title 3");
		
		preview4 = new Preview();
		preview4.setPreviewChapter(4);
		preview4.setPreviewSynopsis("Mock Preview Synopsis 4");
		preview4.setPreviewTitle("Mock Preview Title 4");
		
		preview5 = new Preview();
		preview5.setPreviewChapter(5);
		preview5.setPreviewSynopsis("Mock Preview Synopsis 5");
		preview5.setPreviewTitle("Mock Preview Title 5");
		
		previews.add(preview1);
		previews.add(preview2);
		previews.add(preview3);
		previews.add(preview4);
		previews.add(preview5);
		
		
	}
	
	@Test
	public void getAllPreviewsTest() {
		
		when(previewController.getAllPreviews()).thenReturn(previews);
		previewController.getAllPreviews();
		
		assertEquals(5, previewController.getAllPreviews().size());
		
		for (int i = 1; i <= 5; i++) {
			
			assertEquals(i, previewController.getAllPreviews().get(i - 1).getPreviewChapter());
			assertEquals("Mock Preview Title " + i, previewController.getAllPreviews().get(i - 1).getPreviewTitle());
			assertEquals("Mock Preview Synopsis " + i, previewController.getAllPreviews().get(i - 1).getPreviewSynopsis());
			
		}
		
		verify(previewServiceMock, times(17)).getAllPreviews();
		verifyNoMoreInteractions(previewServiceMock);
		
	}
	
	
	@Test
	public void getSpecificPreviewTest() {
		
		when(previewController.getSpecificPreview(preview1, preview1.getPreviewChapter())).thenReturn(preview1);
		
		previewController.getSpecificPreview(preview1, preview1.getPreviewChapter());
		
		assertEquals(1, previewController.getSpecificPreview(preview1, preview1.getPreviewChapter()).getPreviewChapter());
		assertEquals("Mock Preview Title 1", previewController.getSpecificPreview(preview1, preview1.getPreviewChapter()).getPreviewTitle());
		assertEquals("Mock Preview Synopsis 1", previewController.getSpecificPreview(preview1, preview1.getPreviewChapter()).getPreviewSynopsis());
		
		verify(previewServiceMock, times(4)).getSpecificPreview(preview1);
		
	}
	
	@Test
	public void addPreview() {
		
		Preview preview6 = new Preview();
		preview6.setPreviewChapter(6);
		preview6.setPreviewTitle("Mock Preview Title 6");
		preview6.setPreviewSynopsis("Mock Preview Synopsis 6");
		
		previewController.addPreview(preview6);
		
		verify(previewServiceMock).addPreview(preview6);
		verifyNoMoreInteractions(previewServiceMock);
		
	}
	
	
	
	

}
