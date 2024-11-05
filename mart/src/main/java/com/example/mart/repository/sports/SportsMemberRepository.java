package com.example.mart.repository.sports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entitiy.sports.SportsMember;

public interface SportsMemberRepository extends JpaRepository<SportsMember, Long> {

}
