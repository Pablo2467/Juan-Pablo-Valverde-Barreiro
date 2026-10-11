package com.gym.service;

import com.gym.entity.Member;
import com.gym.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Member findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Member with id " + id + " not found"));
    }

    public Member create(Member data) {
        Member member = new Member();
        copy(data, member);
        return repository.save(member);
    }

    public Member update(Long id, Member data) {
        Member member = findById(id);  
        copy(data, member);
        return repository.save(member);
    }

    public void delete(Long id) {
        repository.delete(findById(id));   
    }

    private void copy(Member from, Member to) {
        to.setFullName(from.getFullName());
        to.setEmail(from.getEmail());
        to.setMembershipType(from.getMembershipType());
        to.setStartDate(from.getStartDate());
    }
}