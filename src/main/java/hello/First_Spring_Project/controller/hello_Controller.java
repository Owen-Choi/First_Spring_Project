package hello.First_Spring_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class hello_Controller {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
        //controller에서 반환하는 문자열 (여기서는 hello)과 일치하는 화면을
        // view resolver가 찾아서 자동으로 렌더링 해준다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model) {
        // 여기서 parameter로 넘어간 name은 html 링크 뒤에 ? 로 지정해준다.
        // ex : http://localhost:8080/hello-mvc?name=spring!
        model.addAttribute("name", name);
        return "hello-template";
    }
}
