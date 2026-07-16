package com.library;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "books")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String title;
	private String author;
	private String isbn;
	private String description;
	private String publisher;
	private LocalDate publishedDate;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	private String language;
	
	//---------- CONSTRUCTORS ----------
	public Book(){

	}

	public Book(String title, String author, String isbn, String description, String publisher, LocalDate publishedDate, Genre genre, String language) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.description = description;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.genre = genre;
		this.language = language;
	}
	
	//---------- HELPER METHODS ----------
	@Override
	public boolean equals(Object o) {
	    if (this == o) {
			return true;
		}

	    if (!(o instanceof Book)) {
			return false;
		}
	    Book b = (Book) o;
	    return isbn.equals(b.isbn);
	}

	@Override
	public int hashCode() {
		return isbn.hashCode();
	}

	//---------- GETTERS & SETTERS ----------
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public String getDescription() {
		return description;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public Genre getGenre() {
		return genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}//end class Book
