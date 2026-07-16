package com.library;
import jakarta.persistence.*;

@Entity
@Table(name = "book_copies")

public class BookCopy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	private boolean available;
	
	//---------- CONSTRUCTORS ----------
	public BookCopy(){
		
	}

	public BookCopy(Book book) {
		this.book = book;
		this.available = true;
	}

	//---------- HELPER METHODS ----------
	public void borrow() {
		available = false;
	}

	public void makeAvailable() {
		available = true;
	}
	
	//---------- GETTERS & SETTERS ----------
	public int getId() {
		return id;
	}
	
	public Book getBook() {
		return book;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}

}//end class BookCopy
