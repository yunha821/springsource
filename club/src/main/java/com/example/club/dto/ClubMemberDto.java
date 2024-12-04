package com.example.club.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClubMemberDto {

    // email 형식인지 검증, 비어 있으면 안됨
    // password, name 비어 있으면 안됨

    @Email
    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @NotBlank(message = "이름을 입력하세요")
    private String name;

    public boolean isFromSocial;

    public ClubMemberDto(String email, String password, String name) {

        this.email = email;
        this.password = password;
        this.name = name;
    }

}
