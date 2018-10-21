package com.humanity.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.dao.ComicDAO;
import com.humanity.model.Comic;

@Service
public class ComicService {

	private static final Logger log = Logger.getLogger(ComicService.class);
	private ComicDAO comicDAO;
	
	
	@Autowired
	public void setComicDAO(ComicDAO comicDAO) {
		
		this.comicDAO = comicDAO;
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Comic> getAllComics() {
		
		return comicDAO.getAllComics();
		
	}
	
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToStore(Comic comic) {
		
		comicDAO.addComicToStore(comic);
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updateComicCredentials(Comic comic) {
		
		comicDAO.updateComicCredentials(comic);
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Comic findComicByChapter(int chapter) {
		
		Comic comic = comicDAO.findComicByChapter(chapter);
		return comic;
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Comic findComicByTitle(String title) {
		
		Comic comic = comicDAO.findComicByTitle(title);
		return comic;
		
	}
	
}
