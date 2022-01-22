package hello.First_Spring_Project.service;

import hello.First_Spring_Project.domain.Member;
import hello.First_Spring_Project.repository.MemberRepository;
import hello.First_Spring_Project.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    @Test
    // test code는 한글로 작성해도 된다.
    void 회원가입() {
        // given, when, then 3단으로 검증하는 것을 추천한다고 한다.
        // given
        // 우리는 회원가입을 테스트한다. "hello"라는 이름의 멤버가 조인하는 상황을 가정하겠다.
        Member member = new Member();
        member.setName("Hello");
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
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}