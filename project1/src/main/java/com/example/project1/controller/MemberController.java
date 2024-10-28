package com.example.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.dto.LoginDto;
import com.example.project1.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void getLogin(LoginDto loginDto) {
        log.info("login 페이지 요청");
    }

    // @PostMapping("/login")
    // public void postLogin(HttpServletRequest request) {
    // log.info("login 요청 - 사용자 입력값 요청");

    // String userid = request.getParameter("userid");
    // String password = request.getParameter("password");

    // log.info("userid : {}, password {}", userid, password);

    // }

    // @PostMapping("/login")
    // public void postLogin(String userid, String password) {
    // log.info("login 요청 - 사용자 입력값 요청");
    // log.info("userid : {}, password {}", userid, password);

    // }

    @PostMapping("/login")
    public String postLogin(@Valid LoginDto loginDto, BindingResult result) {
        log.info("login 요청 - 사용자 입력값 요청");
        log.info("userid : {}, password {}", loginDto.getUserid(), loginDto.getPassword());

        if (result.hasErrors()) {
            return "/member/login";
        }

        return "index";
    }

    @GetMapping("/register")
    public void getRegister(MemberDto memberDto) {
        log.info("register 요청");
    }

    // post / return => 로그인 페이지
    @PostMapping("/register")
    public String postRegister(@Valid MemberDto memberDto, BindingResult result) {
        log.info("register 요청", memberDto);

        if (result.hasErrors()) {
            return "/member/register";
        }

        return "redirect:/member/login"; // redirect : 경로 html (x)
    }

    // @PostMapping("path")
    @PostMapping("path")
    public void method2(MemberDto memberDto) {
        // 1. 입력값 가져오기
        // 2. service 호출 후 결과 받기
        // 3. model.attribute
        // 4. 페이지 이동

    }

}