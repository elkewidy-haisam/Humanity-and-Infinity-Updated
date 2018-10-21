package com.humanity.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="comics")
public class Comic implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -633318048047543487L;

	@Id
	@Column(name="chapter")
	private int chapter;
	
	@Column(name="title")
	private String title;
	
	@Column(name="synopsis")
	private String synopsis;
	
	@Column(name="price")
	private double price;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="carts_comics", joinColumns=@JoinColumn(name="comics_chapter"), inverseJoinColumns=@JoinColumn(name="carts_username"))
	@JsonIgnore
	private List<Cart> cart;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="orders_comics", joinColumns=@JoinColumn(name="comics_chapter"), inverseJoinColumns=@JoinColumn(name="orders_username"))
	@JsonIgnore
	private List<OrderHistory> orderHistories;

	public Comic() {
		super();
		
	}
	
	public Comic(int chapter, String title, String synopsis, double price, List<Cart> cart,
			List<OrderHistory> orderHistories) {
		super();
		this.chapter = chapter;
		this.title = title;
		this.synopsis = synopsis;
		this.price = price;
		this.cart = cart;
		this.orderHistories = orderHistories;
	}
	
	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public List<OrderHistory> getOrderHistories() {
		return orderHistories;
	}

	public void setOrderHistories(List<OrderHistory> orderHistories) {
		this.orderHistories = orderHistories;
	}
	
	
}
