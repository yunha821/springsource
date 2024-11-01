package com.example.project3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project3.entity.Member;
import com.example.project3.entity.Team;

public interface MemberRepository extends JpaRepository<Member, String> {

    // FROM 절에 Entity 이름(대소문자 구별) 작성
    @Query("SELECT m FROM Member m JOIN m.team t WHERE t.name = :name")
    public List<Member> findByMemberEqualTeam(String name);

    // public 생략 가능
    List<Member> findByTeam(Team team);
}
