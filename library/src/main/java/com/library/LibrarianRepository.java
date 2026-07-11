package com.library;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    Optional<Librarian> findByUsername(String username);

}//end class LibrarianRepository