package com.example.club.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.club.dto.ClubAuthMemberDto;
import com.example.club.dto.ClubMemberDto;
import com.example.club.entity.ClubMember;
import com.example.club.entity.constant.ClubRole;
import com.example.club.repository.ClubMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class ClubUserDetailsService implements UserDetailsService, ClubUserService {

    private final ClubMemberRepository clubMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("login {}", username);

        // DB 인증
        Optional<ClubMember> result = clubMemberRepository.findByEmailAndFromSocial(username, false);

        // email or social 정보가 다른 경우 exception 처리
        if (!result.isPresent())
            throw new UsernameNotFoundException("check Email and Social");

        // 일치한다면
        ClubMember clubMember = result.get();

        ClubAuthMemberDto clubAuthMemberDto = new ClubAuthMemberDto(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()));
        clubAuthMemberDto.setName(clubMember.getName());
        clubAuthMemberDto.setFromSocial(clubAuthMemberDto.isFromSocial());
        return clubAuthMemberDto; //// 리턴 값 UserDetails <---- User <---- ClubAuthMemberDto
    }

    @Override
    public String register(ClubMemberDto dto) {

        ClubMember clubMember = ClubMember.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .fromSocial(dto.isFromSocial())
                .password(dto.getPassword())
                .build();
        clubMember.addMemberRole(ClubRole.USER);

        return clubMemberRepository.save(clubMember).getEmail();

    }

}
