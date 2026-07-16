package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByMembershipNumber(String membershipNumber);

}//end class MemberRepository
