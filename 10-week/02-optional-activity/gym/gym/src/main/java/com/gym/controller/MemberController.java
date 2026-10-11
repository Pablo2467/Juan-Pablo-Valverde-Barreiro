package com.gym.controller;

import com.gym.entity.Member;
import com.gym.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Member> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Member create(@Valid @RequestBody Member member) {
        return service.create(member);
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable Long id, @Valid @RequestBody Member member) {
        return service.update(id, member);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}