package hello.First_Spring_Project.controller;

import hello.First_Spring_Project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;
    //private final MemberService memberService = new MemberService();
    // 코드를 위와 같이 짤 필요가 없다고 한다. 왜냐하면 MemberService라는 객체는 별 기능이 없고,
    // 그냥 한번만 생성해서 공용으로 쓰면 되는데 굳이 컨트롤러마다 만들 필요가 없다는 것.
    // 그래서 스프링 컨테이너에 등록을 해두고 쓴다고 한다.

    // MemberService에 @Service 어노테이션이 있어야 스프링에서 클래스를 인식하고 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //Controll 어노테이션이 달려있는 controller의 경우 스프링 컨테이너가 뜰 때 생성을 하고,
    // 그때 위 코드, 즉 생성자를 실행하는데, @AutoWired 어노테이션이 달려있다면 스프링에서
    // 컨테이너에 있는 객체를 자동으로 넣어준다. 이것도 D.I, 의존성 주입이다.
    // 그리고 여기서는 생성자를 통해서 MemberService 객체가 MemberController에 주입이 된다.
    // 이런 것을 D.I 중에서도 생성자 주입이라고 한다.
}
