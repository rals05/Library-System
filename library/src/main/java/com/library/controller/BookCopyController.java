package com.library.controller;

import com.library.BookCopy;
import com.library.service.BookCopyService;
import org.springframework.web.bind.annotation.*;
import com.library.Book;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.library.service.BookService;

@RestController
@RequestMapping("/api/bookcopies")
@CrossOrigin(origins = "*")
public class BookCopyController {

    private final BookCopyService bookCopyService;
    private final BookService bookService;

    // ---------- CONSTRUCTOR ----------
    public BookCopyController(BookCopyService bookCopyService, BookService bookService) {
        this.bookCopyService = bookCopyService;
        this.bookService = bookService;
    }

    // ---------- METHODS ----------
    @GetMapping
    public List<BookCopy> getAllBookCopies() {
        return bookCopyService.getAllBookCopies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCopy> getBookCopyById(@PathVariable Integer id) {
        BookCopy bookCopy = bookCopyService.getBookCopyById(id);

        if(bookCopy == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookCopy);
    }

    @PostMapping
    public BookCopy addBookCopy(@RequestBody BookCopy bookCopy) {
        return bookCopyService.addBookCopy(bookCopy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookCopy(@PathVariable Integer id) {
        bookCopyService.deleteBookCopy(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book/{bookId}")
    public List<BookCopy> getCopiesOfBook(@PathVariable Integer bookId) {
        Book book = bookService.getBookById(bookId);

        if(book == null) {
            return List.of();
        }

        return bookCopyService.getCopiesOfBook(book);
    }

    @GetMapping("/book/{bookId}/available")
    public List<BookCopy> getAvailableCopies(@PathVariable Integer bookId) {
        Book book = bookService.getBookById(bookId);

        if(book == null) {
            return List.of();
        }

        return bookCopyService.getAvailableCopies(book);
    }

}//end class BookCopyController