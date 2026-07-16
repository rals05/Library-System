package com.library.service;

import com.library.Borrow;
import com.library.Member;
import com.library.repository.BorrowRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    // ---------- CONSTRUCTOR ----------
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    // ---------- BORROW MANAGEMENT ----------
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public List<Borrow> getBorrowHistoryForMember(Member member) {
        return borrowRepository.findByMember(member);
    }

    public List<Borrow> getCurrentBorrowsForMember(Member member) {
        return borrowRepository.findByMemberAndReturnDateIsNull(member);
    }

    public List<Borrow> getOverdueBorrows() {
        return borrowRepository.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now());
    }

    public Borrow borrowBook(Borrow borrow) {
        if(!borrow.getBookCopy().isAvailable()) {
            return null;
        }

        borrow.getBookCopy().borrow();

        return borrowRepository.save(borrow);
    }

    public Borrow returnBorrowedBook(int id) {
        Borrow borrow = borrowRepository.findById(id).orElse(null);

        if(borrow == null) {
            return null;
        }

        borrow.markReturned();
        borrow.getBookCopy().makeAvailable();

        return borrowRepository.save(borrow);
    }

}//end class BorrowService