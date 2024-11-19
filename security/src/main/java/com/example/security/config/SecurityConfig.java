package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 웹에 적용할 시큐리티 클래스 포함
@Configuration // 환경설정 파일
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/sample/guest").permitAll()
                        .requestMatchers("/sample/member").hasRole("USER")
                        .requestMatchers("/sample/admin").hasRole("ADMIN"))
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean // 스프링 컨테이너가 관리하는 객체(다른 곳에서 passwordEncoder가 필요할 때 자동으로 주입)
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user1")
                .password("{bcrypt}$2a$10$/QGek4aqp6geZdpdv0JXju7HD4HMM.356jmqZGKxtmlKN8RKHehvC")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);

    }

}
