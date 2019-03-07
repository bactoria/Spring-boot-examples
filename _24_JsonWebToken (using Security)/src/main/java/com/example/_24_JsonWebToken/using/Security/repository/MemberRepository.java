package com.example._24_JsonWebToken.using.Security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example._24_JsonWebToken.using.Security.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

}
