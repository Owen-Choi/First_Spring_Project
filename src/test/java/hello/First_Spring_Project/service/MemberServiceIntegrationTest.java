package hello.First_Spring_Project.service;

import hello.First_Spring_Project.domain.Member;
import hello.First_Spring_Project.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired  MemberRepository memberRepository;
    @Autowired  MemberService memberService;

    @Test
    // @Commit ==> 데이터베이스에 데이터를 commit함. (트랜잭션 커밋)
    void 회원가입() {
        // given, when, then 3단으로 검증하는 것을 추천한다고 한다.
        // given
        // 우리는 회원가입을 테스트한다. "hello"라는 이름의 멤버가 조인하는 상황을 가정하겠다.
        Member member = new Member();
        // AfterEach 메서드가 있어야 DB 충돌 없이 모든 테스트코드가 잘 동작한다.
        // 아래의 member들 이름도 Spring이기 때문.
        member.setName("Spring100");
        // when
        // 무엇을 검증할거냐. 상황을 말하는 듯 하다.
        // 여기서 우리는 회원가입을 검증한다. 따라서 memberService의 join을 테스트한다.
        long id = memberService.join(member);
        // then
        // 이해가 잘 안되는 부분.
        Member findMember = memberService.findOne(member.getId()).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
        //when
        memberService.join(member1);
        /*
        try{
            memberService.join(member2);
        }catch(IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        // 이렇게 try catch를 쓸 수도 있지만 테스트만을 위해서 try catch는 잘 쓰지 않는다고 한다.
        // 하지만 이런 경우를 위해서 문법을 제공한다고 한다. 아래와 같다.
        // 두번째 매개변수로 전달된 람다의 로직을 실행하면서 첫번째의 예외가 터져야 패스된다.
        IllegalStateException e =
                assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // ctrl + alt + v : 반환값 자동 선언
        // 예외 문자로 또 검증을 하고싶다면 다음과 같이 로직을 짤 수 있다.
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }
}
