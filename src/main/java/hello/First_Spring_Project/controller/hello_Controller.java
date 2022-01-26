package hello.First_Spring_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hello_Controller {

    @GetMapping( "hello")
    public String hello(Model model) {
        model.addAttribute("data", "nice to meet you!!");
        return "hello";
        //controller에서 반환하는 문자열 (여기서는 hello)과 일치하는 화면을
        // view resolver가 찾아서 자동으로 렌더링 해준다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model) {
        // 여기서 parameter로 넘어간 name은 html 링크 뒤에 ? 로 지정해준다.
        // ex : http://localhost:8080/hello-mvc?name=spring!
        // 링크 정보를 통해 spring이라는 인자가 name 변수에 할당이 되고
        // hello-template의 name에 대입이 된다.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //아직 자세히는 모르겠지만 JSON 처럼 데이터를 데이터 자체로 전송하는 것을 관장하는 어노테이션 같다.
    // http://localhost:8080/hello-string?name=spring! 페이지의 결과로 hello spring!
    //이 출력되고 페이지 소스 보기를 눌러서 소스를 보면 문자열만 있는 것을 확인할 수 있다.
    public String helloString(@RequestParam("name")String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    // http://localhost:8080/hello-api?name=cheolwoong
    // hello라는 객체를 넘길 수 있다.
    // 넘어간 객체는 JSON의 형식을 따른다.
    public Hello helloapi(@RequestParam("name")String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
