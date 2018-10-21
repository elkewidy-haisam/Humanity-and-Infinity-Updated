package com.humanity.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.model.Preview;

@Repository
public class PreviewDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Preview> getAllPreviews() {
		// TODO Auto-generated method stub
		
		String hql = "FROM Preview";
		
		@SuppressWarnings("unchecked")
		List<Preview> previews = (List<Preview>) sessionFactory.getCurrentSession().createQuery(hql).list();
		
		return previews;
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Preview getSpecificPreview(Preview preview) {
		
		String hql = "from Preview WHERE previewTitle =:title";
		
		Preview preview1 = (Preview) sessionFactory.getCurrentSession().createQuery(hql).setParameter("title", preview.getPreviewTitle()).uniqueResult();
		// TODO Auto-generated method stub
		
		return preview1;
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addPreview(Preview preview) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().save(preview);
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updatePreview(Preview preview) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().saveOrUpdate(preview);
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void deletePreview(Preview preview) {
		
		sessionFactory.getCurrentSession().delete(preview);
	}

}
