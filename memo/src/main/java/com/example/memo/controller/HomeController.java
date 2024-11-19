package com.example.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import groovy.util.logging.Log4j2;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
