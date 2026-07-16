package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.BookCopy;
import java.util.List;
import com.library.Book;

public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {

    List<BookCopy> findByBook(Book book);

    List<BookCopy> findByBookAndAvailableTrue(Book book);

    long countByBook(Book book);

    long countByAvailableTrue();

    long countByAvailableFalse();

}//end class BookCopyRepository