package com.example.project2.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Member;
import com.example.project2.entity.constant.RoleType;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    // C
    @Test
    public void insertTest() {
        // Member member = Member.builder()
        // .id("user1")
        // .username("user1")
        // .age(20)
        // .roleType(RoleType.USER)
        // .build();

        // memberRepository.save(member);

        IntStream.rangeClosed(2, 10)
                .forEach(i -> {
                    Member member = Member.builder()
                            .id("user" + i)
                            .username("user" + i)
                            .age(20 + i)
                            .roleType(RoleType.USER)
                            .createdDate(LocalDateTime.now())
                            .build();
                    memberRepository.save(member);
                });
    }

    // R
    @Test
    public void selectOneTest() {
        Member member = memberRepository.findById("user7").get();
        System.out.println(member);

    }

    @Test
    public void selectAllTest() {
        memberRepository.findAll().forEach(member -> System.out.println(member));
    }

    @Test
    public void updateTest() {
        // save() : insert or update
        // Member member = Member.builder()
        // .id("user8")
        // .age(28)
        // .roleType(RoleType.ADMIN)
        // .username("user8")
        // .lastModifiedDate(LocalDateTime.now())
        // .build();
        // 변경 원하지 않는다면 값 다 설정해줘야 한다.
        // Or 변경 원하는 데이터만 set
        Member member = memberRepository.findById("user9").get();
        member.setRoleType(RoleType.ADMIN);
        member.setLastModifiedDate(LocalDateTime.now());
        memberRepository.save(member);

    }

    @Test
    public void deleteTest() {
        // memberRepository.delete(null);
        memberRepository.deleteById("user10");
    }

}
