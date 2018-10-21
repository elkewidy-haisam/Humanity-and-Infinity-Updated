package com.humanity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.model.Comic;

@Repository
public class ComicDAO {
	
	final static Logger log = Logger.getLogger(ComicDAO.class);
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Comic> getAllComics() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Comic").list();
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToStore(Comic comic) {
		// TODO Auto-generated method stub
		
		System.out.println(comic);
		System.out.println(comic.getTitle());
		System.out.println(comic.getChapter());
		System.out.println(comic.getSynopsis());
		System.out.println(comic.getPrice());
		
		sessionFactory.getCurrentSession().save(comic);
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updateComicCredentials(Comic comic) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().saveOrUpdate(comic);
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Comic findComicByChapter(int chapter) {
		
		Comic comic =  (Comic) sessionFactory.getCurrentSession().get(Comic.class, chapter);
		log.info("Comic title: " + comic.getTitle());
		log.info("Comic chapter: " + comic.getChapter());
		log.info("Comic synopsis: " + comic.getSynopsis());
		return comic;
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Comic findComicByTitle(String title) {
		
		String hql = "from Comic where title =:comicTitle";
		Comic comic =  (Comic) sessionFactory.getCurrentSession().createQuery(hql).setParameter("comicTitle", title).uniqueResult();
		log.info("Comic title: " + comic.getTitle());
		log.info("Comic chapter: " + comic.getChapter());
		log.info("Comic synopsis: " + comic.getSynopsis());
		
		return comic;
		
	}
	

}
