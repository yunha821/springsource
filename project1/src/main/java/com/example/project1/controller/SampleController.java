package com.example.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project1.dto.CalcDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller

public class SampleController {

    @GetMapping("/sample/list")
    public void getList() {
        log.info("list 요청");

    }

    @GetMapping("/sample/main")
    public void getMain() {
        log.info("main 요청");

    }

    // @RequestMapping(path = "/basic", method = RequestMethod.GET)
    // public void basic() {
    // log.info("basic 컨트롤러 동작");
    // }

    // void : templates 폴더 아래 경로로 인식
    // /basic => basic.html
    // /sample/ex2 => /sample/ex2.html

    // String : redirect 하는 경우이거나 temlplate 파일명을 임의대로 지정하는 경우

    // 입력값 가져오기
    // 1) HttpServletRequest 사용 가능(입력값 가져오는 용도로 잘 사용하지 않음)
    // 2) 매개변수 선언(변수명과 이름 맞추는게 편함)
    // 3) DTO 사용(POST 메소드가 끝난 후 보여지는 페이지에서 DTO 사용 가능)
    // CalcDto -> calcDto / LoginDto -> loginDto (앞자리 소문자로 바꾼 후 사용)

    // 컨트롤러가 가지고 있는 값을 화면과 공유하기
    // redirect 로 움직이지 않는 경우
    // 1) ~~DTO : 기본 공유(클래스명과 동일(맨 첫자만 소문자로))
    // 2) 변수에 들어있는 값을 공유 : model.addAttribute("이름", 변수명)
    // model.addAttribute("이름", 객체명)
    // 3) method(@ModelAttribute int bno) : bno 공유하고 싶다면
    // 4) method(@ModelAttribute("u") UserDTO userDto) : UserDto 공유하고 싶은데 이름 다르게 공유

    // redirect 움직이는 경우
    // RedirectAttribute rttr 이용
    // 1) rttr.addAttribute("이름", 값) : 경로에 ? 다음에 따라가는 값의 형태 => ${param.이름}
    // 2) rttr.addFlashAttribute("이름", 값) : 세션을 이용하기 때문에 따라가는건 안 보임 => ${이름}

    // Model 과 RedirectAttribute 차이점
    // 움직이는 방식 / 객체를 담을 수 있느냐? 없느냐?

    @GetMapping("/basic2")
    public String basic2(RedirectAttributes rttr) {
        log.info("basic2 컨트롤러 동작");

        rttr.addAttribute("age", 15); // redirect 시 주소의 파라메터로 딸려 보내기
        rttr.addAttribute("name", "hong"); // redirect 시 주소의 파라메터로 딸려 보내기
        rttr.addFlashAttribute("addr", "seoul");

        // sendRedirect() : redirect:경로
        return "redirect:/ex1"; // http://localhost:8080/ex1?age=15
        // return "redirect:/ex1?age=15"; // http://localhost:8080/ex1?age=15
    }

    @GetMapping("/basic")
    public String basic(RedirectAttributes rttr) {
        log.info("basic 컨트롤러 동작");

        // session 을 사용하는 것과 동일하나 일시적 보관
        rttr.addFlashAttribute("addr", "seoul");

        return "redirect:/ex1";
    }

    @GetMapping("/ex1")
    public void getEx1() {
        log.info("ex1 컨트롤러 동작");
    }

    @GetMapping("/samle/ex2")
    public void getEx2(String param1, String param2) {
        log.info("ex2 컨트롤러 동작");
        log.info("{}, {}", param1, param2);
    }

    @GetMapping("ex3")
    public String getEx3() {
        log.info("ex3 컨트롤러 동작");
        return "test";
    }

    @GetMapping("ex4")
    public String getEx4() {
        log.info("ex4 컨트롤러 동작");
        return "/sample/ex2";
    }

    // calc1 보여주기
    // http://localhost:8080/clac1
    // @GetMapping("calc1")
    // public String getCalc1() {
    // log.info("calc1");
    // return "/sample/calc1";

    // }

    // http://localhost:8080/sample/clac1
    @GetMapping("/sample/calc1")
    public void getCalc1() {
        log.info("calc1 폼 요청");

    }

    // @PostMapping("/sample/calc1")
    // public void postCalc1(int num1, int num2) {
    // log.info("calc 입력값 가져오기");
    // log.info("{} + {} = {}", num1, num2, (num1 + num2));

    // }

    @PostMapping("/sample/calc1")
    public void postCalc1(CalcDto calc, Model model) {
        log.info("calc 입력값 가져오기");
        log.info("{} + {} = {}", calc.getNum1(), calc.getNum2(), (calc.getNum1() + calc.getNum2()));

        int result = calc.getNum1() + calc.getNum2();

        // result 값을 화면에 보여주기
        model.addAttribute("result", result);

    }

    // @PostMapping("/sample/calc1")
    // public void postCalc1(CalcDto calc) {
    // log.info("calc 입력값 가져오기");
    // log.info("{} + {} = {}", calc.getNum1(), calc.getNum2(), (calc.getNum1() +
    // calc.getNum2()));

    // }

    @GetMapping("/sample/calc2")
    public void getCalc2() {
        log.info("calc2 폼 요청");

    }

    // 2번째 /3번째 방법으로 작성
    // @PostMapping("/sample/calc2")
    // public void postCalc2(int num1, int num2, String op) {
    // log.info("calc2 입력값 가져오기");
    // log.info("{} {} {}", num1, op, num2);

    // int result = 0;
    // switch (op) {
    // case "+":
    // result = num1 + num2;
    // break;
    // case "-":
    // result = num1 - num2;
    // break;
    // case "*":
    // result = num1 * num2;
    // break;
    // case "/":
    // result = num1 / num2;
    // break;
    // default:
    // result = num1 % num2;
    // break;
    // }
    // log.info("{} {} {} = {}", num1, op, num2, result);

    // }

    @PostMapping("/sample/calc2")
    public void postCalc2(CalcDto calcDto, Model model) {
        log.info("calc2 입력값 가져오기");
        log.info("{} {} {}", calcDto.getNum1(), calcDto.getOp(), calcDto.getNum2());

        int result = 0;
        switch (calcDto.getOp()) {
            case "+":
                result = calcDto.getNum1() + calcDto.getNum2();
                break;
            case "-":
                result = calcDto.getNum1() - calcDto.getNum2();
                break;
            case "*":
                result = calcDto.getNum1() * calcDto.getNum2();
                break;
            case "/":
                result = calcDto.getNum1() / calcDto.getNum2();
                break;
            default:
                result = calcDto.getNum1() % calcDto.getNum2();
                break;
        }
        log.info("{} {} {} = {}", calcDto.getNum1(), calcDto.getOp(), calcDto.getNum2(), result);

        model.addAttribute("result", result);

    }

}
