package com.library.service;

import com.library.Member;
import com.library.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //---------- CONSTRUCTOR ----------
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
 
    //---------- METHODS ----------
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

}//end class MemberService