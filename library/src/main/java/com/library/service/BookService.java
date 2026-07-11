package com.library.service;

import com.library.Book;
import com.library.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    
    //---------- CONSTRUCTOR ----------
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //---------- METHODS ----------
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
    

}//end class BookService