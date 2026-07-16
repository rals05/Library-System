package com.library.controller;

import com.library.Borrow;
import com.library.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.library.Member;
import com.library.service.MemberService;

@RestController
@RequestMapping("/api/borrows")
@CrossOrigin(origins = "*")
public class BorrowController {

    private final BorrowService borrowService;
    private final MemberService memberService;

    // ---------- CONSTRUCTOR ----------
    public BorrowController(BorrowService borrowService, MemberService memberService) {
        this.borrowService = borrowService;
        this.memberService = memberService;
    }

    // ---------- METHODS ----------

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("/member/{memberId}")
    public List<Borrow> getBorrowHistoryForMember(@PathVariable Integer memberId) {
        Member member = memberService.getMemberById(memberId);

        if(member == null) {
            return List.of();
        }

        return borrowService.getBorrowHistoryForMember(member);
    }

    @GetMapping("/member/{memberId}/current")
    public List<Borrow> getCurrentBorrowsForMember(@PathVariable Integer memberId) {
        Member member = memberService.getMemberById(memberId);

        if(member == null) {
            return List.of();
        }

        return borrowService.getCurrentBorrowsForMember(member);
    }

    @GetMapping("/overdue")
    public List<Borrow> getOverdueBorrows() {
        return borrowService.getOverdueBorrows();
    }

    @PostMapping
    public Borrow borrowBook(@RequestBody Borrow borrow) {
        return borrowService.borrowBook(borrow);
    }

    @PutMapping("/{id}/return")
    public Borrow returnBorrowedBook(@PathVariable int id) {
        return borrowService.returnBorrowedBook(id);
    }

}//end class BorrowController