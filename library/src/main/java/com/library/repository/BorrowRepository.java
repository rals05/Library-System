package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.Borrow;
import java.time.LocalDate;
import java.util.List;
import com.library.Member;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    List<Borrow> findByMember(Member member);

    List<Borrow> findByMemberAndReturnDateIsNull(Member member);

    List<Borrow> findByReturnDateIsNull();

    List<Borrow> findByDueDateBeforeAndReturnDateIsNull(LocalDate date);

}//end class BorrowRepository