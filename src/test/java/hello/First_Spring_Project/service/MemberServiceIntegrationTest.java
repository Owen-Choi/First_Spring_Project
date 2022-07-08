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
        // given
        Member member = new Member();
        member.setName("Spring100");
        // when
        long id = memberService.join(member);
        // then
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

        IllegalStateException e =
                assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
