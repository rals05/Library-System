package com.library.controller;

import com.library.Member;
import com.library.service.MemberService;
import org.springframework.http.ResponseEntity;
import com.library.dto.LoginRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Integer id) {
        Member member = memberService.getMemberById(id);

        if(member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member);
    }

    @PostMapping
    public Member registerMember(@RequestBody Member member) {
        return memberService.registerMember(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Integer id, @RequestBody Member updatedMember) {
        Member member = memberService.updateMember(id, updatedMember);

        if(member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody LoginRequest loginRequest) {
        Member member = memberService.login(loginRequest.getNumber(), loginRequest.getPassword());

        if(member == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(member);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Member> changePassword(@PathVariable Integer id, @RequestParam String newPassword) {
        Member member = memberService.changePassword(id, newPassword);

        if(member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member);
    }

}//end class MemberController