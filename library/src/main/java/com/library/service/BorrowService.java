package com.library.service;

import com.library.Borrow;
import com.library.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    // ---------- CONSTRUCTOR ----------
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    // ---------- METHODS ----------
    public List<Map<String, Object>> getAllBorrows() {

        List<Map<String, Object>> result = new ArrayList<>();

        for (Borrow b : borrowRepository.findAll()) {

            Map<String, Object> map = new HashMap<>();

            map.put("id", b.getId());
            map.put("borrowDate", b.getBorrowDate() != null ? b.getBorrowDate().toString() : null);
            map.put("dueDate", b.getDueDate() != null ? b.getDueDate().toString() : null);
            map.put("returnDate", b.getReturnDate() != null ? b.getReturnDate().toString() : null);
            map.put("returned", b.isReturned());

            if (b.getBookCopy() != null) {
                Map<String, Object> copy = new HashMap<>();
                copy.put("id", b.getBookCopy().getId());

                if (b.getBookCopy().getBook() != null) {
                    copy.put("book", Map.of(
                            "id", b.getBookCopy().getBook().getId(),
                            "title", b.getBookCopy().getBook().getTitle()
                    ));
                }

                map.put("bookCopy", copy);
            }

            if (b.getMember() != null) {
                map.put("member", Map.of(
                        "name", b.getMember().getName(),
                        "membershipNumber", b.getMember().getMembershipNumber()
                ));
            }

            result.add(map);
        }

        return result;
    }

    public Borrow addBorrow(Borrow borrow) {
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusDays(14));
        return borrowRepository.save(borrow);
    }

    public Borrow returnBorrow(int id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow();
        borrow.markReturned();
        return borrowRepository.save(borrow);
    }

}//end class BorrowService