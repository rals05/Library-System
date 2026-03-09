package Library;
import java.time.LocalDate;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private String isbn;
	private String description;
	private String publisher;
	private LocalDate publishedDate;
	
	//---------- CONSTRUCTOR ----------
	public Book(int id, String title, String author, String isbn, String description, String publisher, LocalDate publishedDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.description = description;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
	}
	
	//---------- HELPER METHOD ----------
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Book)) return false;
	    Book b = (Book) o;
	    return isbn.equals(b.isbn);
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

}//end class Book
