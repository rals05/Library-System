package com.library.service;

import com.library.Member;
import com.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //---------- CONSTRUCTOR ----------
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
 
    //---------- MEMBER MANAGEMENT ----------
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Integer id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Integer id, Member updatedMember) {
        Member member = memberRepository.findById(id).orElse(null);

        if(member == null) {
            return null;
        }

        member.setName(updatedMember.getName());
        member.setEmail(updatedMember.getEmail());
        member.setPhoneNumber(updatedMember.getPhoneNumber());

        return memberRepository.save(member);
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    //---------- LOGIN ----------
    public Member login(String membershipNumber, String password) {
        Member member = memberRepository.findByMembershipNumber(membershipNumber);

        if(member == null) {
            return null;
        }

        if(!member.getPassword().equals(password)) {
            return null;
        }

        return member;
    }

    //---------- UPDATE PROFILE ----------
    public Member changePassword(Integer id, String newPassword) {
        Member member = memberRepository.findById(id).orElse(null);

        if(member == null) {
            return null;
        }

        member.setPassword(newPassword);
        return memberRepository.save(member);
    }


}//end class MemberService