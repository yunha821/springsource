package com.example.movie.dto;

import java.time.LocalDateTime;

import com.example.movie.entity.constant.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
    private Long mid;

    private String email;

    private String password;
    private String nickname;

    private MemberRole role;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
