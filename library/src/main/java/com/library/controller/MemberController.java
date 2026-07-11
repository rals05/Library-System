package com.library.controller;

import com.library.Member;
import com.library.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;
    
    //---------- CONSTRUCTOR ----------
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //---------- METHODS ----------
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

}//end class MemberController