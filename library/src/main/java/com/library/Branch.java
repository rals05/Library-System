package com.library;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "branches")

public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	@OneToMany(mappedBy = "branch")
	private List<BookCopy> bookCopies;
	
	//---------- CONSTRUCTORS ----------
	public Branch(){

	}
	
	public Branch(String name, String address) {
		this.name = name;
		this.address = address;
		this.bookCopies = new ArrayList<>();
	}
	
	//---------- BOOK COPY MANAGEMENT ----------
	public void addBookCopy(BookCopy copy) {
		bookCopies.add(copy);
	}
	
	public void removeBookCopy(BookCopy copy) {
		bookCopies.remove(copy);
	}
	
	//---------- GETTERS & SETTERS ----------
	public List<BookCopy> getAllBookCopies(){
		return new ArrayList<>(bookCopies);
	}
	
	public List<BookCopy> getAllAvailableBookCopies(Book book){
		List<BookCopy> availableCopies = new ArrayList<>();
		
		for(BookCopy bookCopy : bookCopies) {
			if(bookCopy.getBook().equals(book) && bookCopy.getIsAvailable()) {
				availableCopies.add(bookCopy);
			}
		}
		
		return availableCopies;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

}//end class Branch
