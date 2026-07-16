package com.library;
import java.time.LocalDate;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "borrows")

public class Borrow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name = "book_copy_id")
	private BookCopy bookCopy;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	
	//---------- CONSTRUCTORS ----------
	public Borrow(){

	}
	
	public Borrow(BookCopy bookCopy, Member member, int borrowedDurationDays) {
		this.bookCopy = bookCopy;
		this.member = member;
		this.borrowDate = LocalDate.now();
		this.dueDate = borrowDate.plusDays(borrowedDurationDays);
		bookCopy.setAvailable(false);
	}
	
	//---------- HELPER METHODS ----------
	public boolean isReturned() {
		return returnDate != null;
	}
	
	public boolean isOverdue() {
		return !isReturned() && LocalDate.now().isAfter(dueDate);
	}
	
	public void markReturned() {
		this.returnDate = LocalDate.now();
	    bookCopy.setAvailable(true);
	}
	
	//---------- GETTERS & SETTERS ----------
	public int getId() {
		return id;
	}
	
	public BookCopy getBookCopy() {
		return bookCopy;
	}
	
	public Member getMember() {
		return member;
	}
	
	public LocalDate getBorrowDate() {
		return borrowDate;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

}//end class Borrow
