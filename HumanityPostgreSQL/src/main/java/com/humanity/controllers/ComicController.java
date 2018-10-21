package com.humanity.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.humanity.model.Comic;
import com.humanity.services.ComicService;

@RestController
@RequestMapping("/humanity")
public class ComicController {
	
	private static final Logger log = Logger.getLogger(CartController.class);
	
	@Autowired
	private ComicService comicService;

	
	public void setComicService(ComicService comicService) {
		
		this.comicService = comicService;
		System.out.println("Comic Service: " + this.comicService);
		
	}
	
	
	@RequestMapping(value="/comics/getAllComics", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Comic> getAllComics() {
		
		
		if (comicService == null) {
			
			System.out.println("ComicController, comicService is not there.");
			
		} else {
			
			System.out.println("ComicController, comicService is there.");
			
		}
		
		
		return comicService.getAllComics();
		
	}
	
	
	@RequestMapping(value="/comics/addComicToStore", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToStore(@Valid @RequestBody Comic comic) {
		
		comicService.addComicToStore(comic);
		
	}
	
	
	@RequestMapping(value="/comics/updateComic", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updateComicCredentials(@Valid @RequestBody Comic comic) {
		
		comicService.updateComicCredentials(comic);
		
	}
	
	
	@RequestMapping(value="/comics/findComicByChapter/{chapter}", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Comic findComicByChapter(@PathVariable int chapter) {
		
		return comicService.findComicByChapter(chapter);
		
	}
	
	
	@RequestMapping(value="/comics/findComicByTitle/{title}", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Comic findComicByTitle(@PathVariable String title) {
		
		return comicService.findComicByTitle(title);
		
	}

}
