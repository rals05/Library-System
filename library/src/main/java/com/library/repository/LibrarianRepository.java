package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.Librarian;
import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    Librarian findByEmployeeNumber(String employeeNumber);

}//end class LibrarianRepository