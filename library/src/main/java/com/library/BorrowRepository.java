package com.library;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {}