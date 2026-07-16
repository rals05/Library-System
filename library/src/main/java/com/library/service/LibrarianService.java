package com.library.service;

import com.library.Librarian;
import com.library.repository.LibrarianRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibrarianService {

    private final LibrarianRepository librarianRepository;

    // ---------- CONSTRUCTOR ----------
    public LibrarianService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    // ---------- LIBRARIAN MANAGEMENT ----------
    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    public Librarian getLibrarianById(Integer id) {
        return librarianRepository.findById(id).orElse(null);
    }

    public Librarian addLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    public Librarian updateLibrarian(Integer id, Librarian updatedLibrarian) {
        Librarian librarian = librarianRepository.findById(id).orElse(null);

        if(librarian == null) {
            return null;
        }

        librarian.setName(updatedLibrarian.getName());
        librarian.setEmail(updatedLibrarian.getEmail());
        librarian.setPhoneNumber(updatedLibrarian.getPhoneNumber());

        return librarianRepository.save(librarian);
    }

    public void deleteLibrarian(Integer id) {
        librarianRepository.deleteById(id);
    }

    // ---------- LOGIN ----------
    public Librarian login(String employeeNumber, String password) {
        Librarian librarian = librarianRepository.findByEmployeeNumber(employeeNumber);

        if(librarian == null) {
            return null;
        }

        if(!librarian.getPassword().equals(password)) {
            return null;
        }

        return librarian;
    }

    //---------- UPDATE PROFILE ----------
    public Librarian changePassword(Integer id, String newPassword) {
        Librarian librarian = librarianRepository.findById(id).orElse(null);

        if(librarian == null) {
            return null;
        }

        librarian.setPassword(newPassword);
        return librarianRepository.save(librarian);
    }

}//end class LibrarianService