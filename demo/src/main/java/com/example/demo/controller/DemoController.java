package com.example.demo.controller;

import java.util.List;  // 변경된 부분
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.model.domain.TestDB;
import com.example.demo.model.service.TestService;

@Controller  // 컨트롤러 이노테이션 명시
public class DemoController {

    @Autowired
    TestService testService;

    @GetMapping("/hello") // 전송방식 GET
    public String hello(Model model) {
        model.addAttribute("data", "방갑습니다."); // model 설정
        return "hello"; // hello.html연결
    }
    
    @GetMapping("/hello2") 
    public String hello2(Model model) {
        String message = "홍길동님. \n\n방갑습니다. \n\n오늘. \n\n날씨는. \n\n매우 좋습니다.";
        message = message.replace("\n","<br>");
        model.addAttribute("data", message);
        return "hello2";
    }
    
    @GetMapping("/about_detailed") // 전송방식 GET
    public String about() {
        return "about_detailed"; // hello.html연결
    }
    
    @GetMapping("/")
    public String index() {
        return "index";  // templates/index.html 파일을 렌더링
    }
    
    @GetMapping("/test1")
    public String thymeleaf_test1(Model model) {
        model.addAttribute("data1", "<h2> 방갑습니다 </h2>");
        model.addAttribute("data2", "태그의 속성 값");
        model.addAttribute("link", 01);
        model.addAttribute("name", "홍길동");
        model.addAttribute("para1", "001");
        model.addAttribute("para2", 002);
        return "thymeleaf_test1";
    }
    
    @GetMapping("/testdb")
    public String getAllUsers(Model model) {
        List<TestDB> users = testService.findAll(); // 모든 데이터 조회
        model.addAttribute("users", users);  // 모델에 데이터 추가
        return "testdb"; // 템플릿 파일명
    }
}
