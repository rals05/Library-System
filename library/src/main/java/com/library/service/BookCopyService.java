package com.library.service;

import com.library.BookCopy;
import com.library.Book;
import com.library.repository.BookCopyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    // ---------- CONSTRUCTOR ----------
    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    // ---------- BOOKCOPY MANAGEMENT ----------
    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public BookCopy getBookCopyById(Integer id) {
        return bookCopyRepository.findById(id).orElse(null);
    }

    public BookCopy addBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public void deleteBookCopy(Integer id) {
        bookCopyRepository.deleteById(id);
    }

    //---------- AVAILABILITY ----------
    public List<BookCopy> getCopiesOfBook(Book book) {
        return bookCopyRepository.findByBook(book);
    }

    public List<BookCopy> getAvailableCopies(Book book) {
        return bookCopyRepository.findByBookAndAvailableTrue(book);
    }

    public long getNumberOfCopies(Book book) {
        return bookCopyRepository.countByBook(book);
    }

}//end class BookCopyService