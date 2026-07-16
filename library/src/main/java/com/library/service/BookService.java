package com.library.service;

import com.library.Book;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    
    //---------- CONSTRUCTOR ----------
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //---------- BOOK MANAGEMENT ----------
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Integer id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElse(null);

        if(book == null){
            return null;
        }

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setDescription(updatedBook.getDescription());
        book.setPublisher(updatedBook.getPublisher());
        book.setPublishedDate(updatedBook.getPublishedDate());
        book.setGenre(updatedBook.getGenre());
        book.setLanguage(updatedBook.getLanguage());

        return bookRepository.save(book);
    }
    
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String keyword) {

        Set<Book> books = new HashSet<>();

        books.addAll(bookRepository.findByTitleContainingIgnoreCase(keyword));
        books.addAll(bookRepository.findByAuthorContainingIgnoreCase(keyword));

        return new ArrayList<>(books);
    }
    
}//end class BookService