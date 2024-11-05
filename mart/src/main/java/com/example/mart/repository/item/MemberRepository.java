package com.example.mart.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entitiy.item.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
