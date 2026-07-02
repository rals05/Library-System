package com.library;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {}