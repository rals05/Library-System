package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.library.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

}//end class BookRepository