package com.jshi.laughtale.member.repository;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.member.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndProvider(String email, Provider provider);
}
