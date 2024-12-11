package com.example.movie.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PasswordDto;
import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;

public interface MemberService {

    // 닉네임 수정
    void nickNickUpdate(MemberDto memberDto);

    // 비밀번호 수정
    void passwordUpdate(PasswordDto passwordDto) throws Exception;

    // 회원탈퇴
    void leave(PasswordDto passwordDto) throws Exception;

    // 회원가입
    String register(MemberDto memberDto);

    default Member dtoToEntity(MemberDto memberDto) {
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .role(MemberRole.MEMBER)
                .build();
        return member;
    }

    // default MemberDto entityToDto(Member member) {
    // MemberDto memberDto = MemberDto.builder()
    // .mid(member.getMid())
    // .email(member.getEmail())
    // .password(member.getPassword())
    // .nickname(member.getNickname())
    // .role(MemberRole.MEMBER)
    // .build();
    // return memberDto;
    // }
}
