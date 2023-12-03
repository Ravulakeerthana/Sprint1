package com.anp.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "book")
public class BookStore {
	
	
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int bookid;
    
	private String bookname;
	
	@Column (name = "book _author")
	private String authorname;
	
	private double price;
	
	private int stock;
	
	
	
	
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	
	@Override
	public String toString() {
		return "BookStore [bookid=" + bookid + ", bookname=" + bookname + ", authorname=" + authorname + ", price="
				+ price + ", stock=" + stock + "]";
	}
	
	
	
	public BookStore(int bookid,String bookname,String authorname,double price,int stock) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		this.authorname = authorname;
		this.price = price;
		this.stock = stock;  
		
		
	}
	
	public BookStore() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	
}
