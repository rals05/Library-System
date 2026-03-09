package Library;
import java.time.LocalDate;

public class Borrow {
	
	private int id;
	private BookCopy bookCopy;
	private Member member;
	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	
	//---------- CONSTRUCTORS ----------
	//Creating a new borrow
	public Borrow(BookCopy bookCopy, Member member, int borrowedDays) {
		this.bookCopy = bookCopy;
		this.member = member;
		this.borrowDate = LocalDate.now();
		this.dueDate = borrowDate.plusDays(borrowedDays);
	}
	
	//Loading a borrow
	public Borrow(int id, BookCopy bookCopy, Member member, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate) {
		this.id = id;
		this.bookCopy = bookCopy;
		this.member = member;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}
	
	//---------- METHODS ----------
	public boolean isReturned() {
		return returnDate != null;
	}
	
	public boolean isOverdue() {
		return !isReturned() && LocalDate.now().isAfter(dueDate);
	}
	
	public void markReturned() {
		this.returnDate = LocalDate.now();
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
