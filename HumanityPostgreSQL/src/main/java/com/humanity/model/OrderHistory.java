package com.humanity.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orders")
public class OrderHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2300279579722358276L;

	@Id
	@Column(name="username")
	private String username;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="orders_comics", joinColumns=@JoinColumn(name="orders_username"), inverseJoinColumns=@JoinColumn(name="comics_chapter"))
	private List<Comic> ohComics;

	
	public OrderHistory() {
		super();
	}

	public OrderHistory(String username, List<Comic> ohComics) {
		super();
		this.username = username;
		this.ohComics = ohComics;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comic> getOhComics() {
		return ohComics;
	}

	public void setOhComics(List<Comic> ohComics) {
		this.ohComics = ohComics;
	}
	
}
