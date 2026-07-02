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
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
	private boolean isAvailable;
	
	//---------- CONSTRUCTORS ----------
	public BookCopy(){
		
	}

	public BookCopy(Book book, Branch branch) {
		this.book = book;
		this.branch = branch;
		isAvailable = true;
	}
	
	//---------- GETTERS & SETTERS ----------
	public int getId() {
		return id;
	}
	
	public Book getBook() {
		return book;
	}
	
	public Branch getBranch() {
		return branch;
	}
	
	public boolean getIsAvailable() {
		return isAvailable;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public void setAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}//end class BookCopy
