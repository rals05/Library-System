package com.library.service;

import com.library.dto.DashboardStatsDTO;
import org.springframework.stereotype.Service;

import com.library.repository.BookRepository;
import com.library.repository.BookCopyRepository;
import com.library.repository.MemberRepository;
import com.library.repository.LibrarianRepository;
import com.library.repository.BorrowRepository;
import java.time.LocalDate;

@Service
public class DashboardService {

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final MemberRepository memberRepository;
    private final LibrarianRepository librarianRepository;
    private final BorrowRepository borrowRepository;

    //---------- CONSTRUCTOR ----------
    public DashboardService(BookRepository bookRepository,
                            BookCopyRepository bookCopyRepository, 
                            MemberRepository memberRepository, 
                            LibrarianRepository librarianRepository, 
                            BorrowRepository borrowRepository) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.memberRepository = memberRepository;
        this.librarianRepository = librarianRepository;
        this.borrowRepository = borrowRepository;
    }

    //---------- METHODS ----------
    public DashboardStatsDTO getDashboardStats() {
        DashboardStatsDTO stats = new DashboardStatsDTO();

        stats.setTotalBooks((int) bookRepository.count());
        stats.setTotalCopies((int) bookCopyRepository.count());
        stats.setAvailableCopies((int) bookCopyRepository.countByAvailableTrue());
        stats.setBorrowedCopies((int) bookCopyRepository.countByAvailableFalse());
        stats.setTotalMembers((int) memberRepository.count());
        stats.setTotalLibrarians((int) librarianRepository.count());
        stats.setOverdueBooks(borrowRepository.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now()).size());
        
        return stats;
    }

}//end class DashboardService