package com.library.service;

import com.library.BookCopy;
import com.library.BookCopyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    // ---------- CONSTRUCTOR ----------
    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    // ---------- METHODS ----------
    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public BookCopy addBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

}//end class BookCopyService