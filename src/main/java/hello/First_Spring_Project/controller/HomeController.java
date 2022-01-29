package hello.First_Spring_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // "/"의 의미는 도메인의 첫번째, 즉 localhost:8080으로 들어오면 처음으로 "/"이 매핑된
    // 컨트롤러가? 호출된다고 한다.
    // 또한 컨트롤러가 static 페이지보다 우선순위가 높기 때문에 static이 있어도 "/"이 매핑된 페이지가 먼저 호출된다.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
