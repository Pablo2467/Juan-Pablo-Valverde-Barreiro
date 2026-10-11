package com.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gym.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByFullNameContainingIgnoreCase(String fullname);
    
}
