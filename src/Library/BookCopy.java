package Library;

public class BookCopy {
	
	private int id;
	private Book book;
	private Branch branch;
	private boolean isAvailable;
	
	//---------- CONSTRUCTORS ----------
	//Adding a new book copy
	public BookCopy(Book book, Branch branch) {
		this.book = book;
		this.branch = branch;
		isAvailable = true;
	}
	
	//Loading an existing book copy
	public BookCopy(int id, Book book, Branch branch, boolean isAvailable) {
		this.id = id;
		this.book = book;
		this.branch = branch;
		this.isAvailable = isAvailable;
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
