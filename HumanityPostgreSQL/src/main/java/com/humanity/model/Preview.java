package com.humanity.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="previews")
public class Preview implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4578139742500597521L;

	@Id
	@Column(name="previews_chapter")
	private int chapter;
	
	@Column(name="previews_title")
	private String title;
	
	@Column(name="previews_synopsis")
	private String synopsis;
	
	@Column(name="previews_releasedate")
	@Temporal(TemporalType.DATE)
	private Date release_date;
	
	
	public Preview() {
		super();
	}

	public Preview(int chapter, String title, Date release_date, String synopsis) {
		super();
		this.chapter = chapter;
		this.title = title;
		this.release_date = release_date;
		this.synopsis = synopsis;
		
	}

	public int getPreviewChapter() {
		return chapter;
	}

	public void setPreviewChapter(int chapter) {
		this.chapter = chapter;
	}

	public String getPreviewTitle() {
		return title;
	}

	public void setPreviewTitle(String title) {
		this.title = title;
	}

	public Date getPreviewReleaseDate() {
		return release_date;
	}

	public void setPreviewReleaseDate(Date release_date) {
		this.release_date = release_date;
	}

	public String getPreviewSynopsis() {
		return synopsis;
	}

	public void setPreviewSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

}
