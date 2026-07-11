package com.library.controller;

import com.library.BookCopy;
import com.library.service.BookCopyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookcopies")
@CrossOrigin(origins = "*")
public class BookCopyController {

    private final BookCopyService bookCopyService;

    // ---------- CONSTRUCTOR ----------
    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    // ---------- METHODS ----------
    @GetMapping
    public List<BookCopy> getAllBookCopies() {
        return bookCopyService.getAllBookCopies();
    }

    @PostMapping
    public BookCopy addBookCopy(@RequestBody BookCopy bookCopy) {
        return bookCopyService.addBookCopy(bookCopy);
    }

}//end class BookCopyController