package com.library;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LibraryController {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BorrowRepository borrowRepository;
    private final BookCopyRepository bookCopyRepository;

    public LibraryController(BookRepository bookRepository,
                              MemberRepository memberRepository,
                              BorrowRepository borrowRepository,
                              BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.borrowRepository = borrowRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    // ---- BOOKS ----
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // ---- MEMBERS ----
    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @PostMapping("/members")
    public Member addMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    // ---- BORROWS ---- 
    @GetMapping("/borrows")
    public List<java.util.Map<String, Object>> getAllBorrows() {
    List<java.util.Map<String, Object>> result = new java.util.ArrayList<>();
    for (Borrow b : borrowRepository.findAll()) {
        java.util.Map<String, Object> map = new java.util.HashMap<>();
        map.put("id", b.getId());
        map.put("borrowDate", b.getBorrowDate() != null ? b.getBorrowDate().toString() : null);
        map.put("dueDate", b.getDueDate() != null ? b.getDueDate().toString() : null);
        map.put("returnDate", b.getReturnDate() != null ? b.getReturnDate().toString() : null);
        map.put("returned", b.isReturned());
        if (b.getBookCopy() != null) {
            java.util.Map<String, Object> copy = new java.util.HashMap<>();
            copy.put("id", b.getBookCopy().getId());
            if (b.getBookCopy().getBook() != null) {
                copy.put("book", java.util.Map.of("id", b.getBookCopy().getBook().getId(), "title", b.getBookCopy().getBook().getTitle()));
            }
            map.put("bookCopy", copy);
        }
        if (b.getMember() != null) {
            map.put("member", java.util.Map.of("name", b.getMember().getName(), "membershipNumber", b.getMember().getMembershipNumber()));
        }
        result.add(map);
    }
    return result;
}

    @PostMapping("/borrows")
    public Borrow addBorrow(@RequestBody Borrow borrow) {
        borrow.setBorrowDate(java.time.LocalDate.now());
        borrow.setDueDate(java.time.LocalDate.now().plusDays(14));
        return borrowRepository.save(borrow);
    }

    @PutMapping("/borrows/{id}/return")
    public Borrow returnBorrow(@PathVariable int id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow();
        borrow.markReturned();
        return borrowRepository.save(borrow);
    }

    // ---- BOOK COPIES ----
    @GetMapping("/bookcopies")
    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }
    
    @PostMapping("/bookcopies")
    public BookCopy addBookCopy(@RequestBody BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

}