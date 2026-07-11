package com.library.controller;

import com.library.Borrow;
import com.library.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/borrows")
@CrossOrigin(origins = "*")
public class BorrowController {

    private final BorrowService borrowService;

    // ---------- CONSTRUCTOR ----------
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    // ---------- METHODS ----------

    @GetMapping
    public List<Map<String, Object>> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @PostMapping
    public Borrow addBorrow(@RequestBody Borrow borrow) {
        return borrowService.addBorrow(borrow);
    }

    @PutMapping("/{id}/return")
    public Borrow returnBorrow(@PathVariable int id) {
        return borrowService.returnBorrow(id);
    }

}//end class BorrowController