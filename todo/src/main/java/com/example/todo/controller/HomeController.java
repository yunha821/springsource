package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/") // http://localhost:8080/
    public String getHome() {
        return "redirect:/todo/list"; // http://localhost:8080/todo/list
    }

}
