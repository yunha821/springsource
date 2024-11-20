package com.example.club.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// UserDetails <---- User <---- ClubAuthMemberDto

@ToString
@Setter
@Getter
public class ClubAuthMemberDto extends User {

    private String email; // 아이디 역할
    private String name;
    private boolean fromSocial;

    // security 에서는 username == id
    public ClubAuthMemberDto(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }

}
