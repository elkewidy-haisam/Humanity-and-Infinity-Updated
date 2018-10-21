package com.humanity.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="carts")
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 306172846689481219L;

	@Id
	@Column(name="username")
	@NotNull
	private String cartUser;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="CARTS_COMICS", joinColumns=@JoinColumn(name="CARTS_USERNAME"), inverseJoinColumns=@JoinColumn(name="COMICS_CHAPTER"))
	private List<Comic> cartComics;
	
	public Cart() {
		super();
	}

	public Cart(String cartUser, List<Comic> cartComics) {
		super();
		this.cartUser = cartUser;
		this.cartComics = cartComics;
	}

	public String getCartUser() {
		return cartUser;
	}

	public void setCartUser(String cartUser) {
		this.cartUser = cartUser;
	}

	public List<Comic> getCartComics() {
		return cartComics;
	}

	public void setCartComics(List<Comic> cartComics) {
		this.cartComics = cartComics;
	}
	
	
	
	
}
