package com.library.controller;

import com.library.Librarian;
import com.library.service.LibrarianService;
import org.springframework.http.ResponseEntity;
import com.library.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/librarians")
@CrossOrigin(origins = "*")
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @GetMapping
    public List<Librarian> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Librarian> getLibrarianById(@PathVariable Integer id) {
        Librarian librarian = librarianService.getLibrarianById(id);

        if(librarian == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(librarian);
    }

    @PostMapping
    public Librarian addLibrarian(@RequestBody Librarian librarian) {
        return librarianService.addLibrarian(librarian);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Librarian> updateLibrarian(@PathVariable Integer id, @RequestBody Librarian updatedLibrarian) {
        Librarian librarian = librarianService.updateLibrarian(id, updatedLibrarian);

        if(librarian == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(librarian);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibrarian(@PathVariable Integer id) {
        librarianService.deleteLibrarian(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Librarian> login(@RequestBody LoginRequest loginRequest) {

        Librarian librarian = librarianService.login(loginRequest.getNumber(), loginRequest.getPassword());

        if(librarian == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(librarian);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Librarian> changePassword(@PathVariable Integer id, @RequestParam String newPassword) {
        Librarian librarian = librarianService.changePassword(id, newPassword);

        if(librarian == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(librarian);
    }

}//end class LibrarianController