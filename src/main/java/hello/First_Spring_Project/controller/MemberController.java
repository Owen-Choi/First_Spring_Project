package hello.First_Spring_Project.controller;

import hello.First_Spring_Project.domain.Member;
import hello.First_Spring_Project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    // 매핑 어노테이션이 붙어있는 함수의 return값은 template에서 view resolver가 찾는다.
    // 즉 template에서 members 디렉토리 하위의 createMemberForm을 찾아서 html로 뿌린다.
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 기본적으로 URL 창에다 엔터를 치는 것은 GetMapping이라고 하고, 따라서 get은 조회할 때 쓴다고 한다.
    // PostMapping은 데이터를 어떤 form으로 담아서 전달할 때 쓴다.
    // 즉 위의 GetMapping과 URL은 같지만 Get은 URL을 띄워주는 것,
    // Post는 URL 안에서 데이터를 전달받아 내부로직을 실행하는 것으로 이해해도 될 것 같다.
    // 간단하게 생각하면, 그냥 PostMapping으로 값을 전달받고 Service의 로직과 연계를 하는 등 필요한 곳에 사용하면 되는 것.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        // "redirect:/"는 홈 화면으로 보내는 것.
        return "redirect:/";
    }

    // model.addAttribute 코드를 통해서 모델에 list 값을 전달한다.
    // 그리고 타임리프 템플릿이 members/memberList 페이지에서 리스트를 모두 출력한다.(html 코드 참고)
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
