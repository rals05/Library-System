package Library;
import java.util.ArrayList;
import java.util.List;

public class Library {
	
	private List<Book> books = new ArrayList<>();
	private List<Branch> branches = new ArrayList<>();
	private List<Member> members = new ArrayList<>();
	private List<Borrow> borrowedBooks = new ArrayList<>();
	
	//---------- BOOK MANAGEMENT ----------
	public boolean addBook(Book book) {
		if(!books.contains(book)) {
			books.add(book);
			return true;
		}
		else
			return false;
	}
	
	public boolean removeBook(Book book) {
		if(books.contains(book)) {
			books.remove(book);
			return true;
		}
		else
			return false;
	}
	
	public Book findBookById(int id) {
		for(Book book : books) {
			if(book.getId() == id) {
				return book;
			}
		}
		return null;
	}
	
	public List<Book> findBookByKeyword(String keyword){
		List<Book> result = new ArrayList<>();
		String k = keyword.toLowerCase();
		
		for(Book book: books) {
			if(book.getTitle().toLowerCase().contains(k) || book.getAuthor().toLowerCase().contains(k) || book.getIsbn().contains(k)) {
				result.add(book);
			}
		}
		
		return result;
	}
	
	public List<Book> getBooks(){
		return books;
	}
	
	//---------- BOOK COPY MANAGEMENT ----------
	//add one copy
	public BookCopy addBookCopy(Book book, Branch branch) {
		addBook(book);
		
		BookCopy bookCopy = new BookCopy(book, branch);
		branch.addBookCopy(bookCopy);
		
		return bookCopy;
	} 
	//add more than one copy
	public void addBookCopies(Book book, Branch branch, int quantity) {
		for(int i = 0; i < quantity; i++) {
			addBookCopy(book, branch);
		}
	}
	
	public void removeBookCopy(BookCopy bookCopy) {
		bookCopy.getBranch().removeBookCopy(bookCopy);
	}
	
	//---------- BRANCH MANAGEMENT ----------
	public void addBranch(Branch branch) {
		branches.add(branch);
	}
	
	public void removeBranch(Branch branch) {
		if(branches.contains(branch))
			branches.remove(branch);
	}
	
	public List<Branch> getBranches(){
		return branches;
	}
	
	//---------- MEMBER MANAGEMENT ----------
	public boolean addMember(Member member) {
		for(Member m : members) {
			if(m.getMembershipNumber().equals(member.getMembershipNumber())) {
				return false;
			}
		}
		members.add(member);
		return true;
	}
	
	public boolean removeMember(Member member) {
		if(members.contains(member)) {
			members.remove(member);
			return true;
		}
		else
			return false;
	}
	
	public Member findMemberById(String membershipNumber) {
		for(Member member : members) {
			if(member.getMembershipNumber().equals(membershipNumber)) {
				return member;
			}
		}
		return null;
	}
	
	public List<Member> getMembers(){
		return members;
	}
	
	//---------- BORROW/RETURN MANAGEMENT ----------
	public Borrow borrowBook(Book book, Member member, Branch branch, int borrowedDays) {
		List<BookCopy> availableCopies = branch.getAllAvailableBookCopies(book);
		if(availableCopies.isEmpty()) {
			return null;
		}
		
		BookCopy borrowedBookCopy = availableCopies.get(0);
		borrowedBookCopy.setAvailable(false);
		
		Borrow borrowedBook = new Borrow(borrowedBookCopy, member, borrowedDays);
		borrowedBooks.add(borrowedBook);
		
		return borrowedBook;
	}  
	
	public boolean returnBook(Borrow borrow) {
		if(borrow == null || borrow.isReturned()) {
			return false;
		}
		
		borrow.markReturned();
		borrow.getBookCopy().setAvailable(true);
		borrowedBooks.remove(borrow);
		
		return true;
	}
	
	public List<Borrow> getBorrowedBooks() {
		return borrowedBooks;
	}
	
	//---------- GENEREATE UNIQUE ID ----------
	public static String generateUniqueId(String firstDigit) {
		StringBuilder sb = new StringBuilder(firstDigit);
		int numberOfDigits = 9;
		for(int i = 0; i < numberOfDigits; i++) {
			sb.append((int)(Math.random() * 10));
		}
		
		return sb.toString();
	}

}//end class Library
