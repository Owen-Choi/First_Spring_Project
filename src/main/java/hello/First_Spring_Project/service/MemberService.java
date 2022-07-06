package hello.First_Spring_Project.service;

import hello.First_Spring_Project.domain.Member;
import hello.First_Spring_Project.repository.MemberRepository;
import hello.First_Spring_Project.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 서비스의 경우는 조금 더 비즈니스 용어를 많이 쓴다고 한다.
// 그래야 기획자가 "회원가입 쪽 이상해요"할때 일대일로 매칭돼서 찾을 수 있기 때문이라고 한다.
// ** ctrl + shift + t 단축키를 이용하여 현재 클래스와 매칭되는 테스트 클래스를 바로 만들 수 있다. **

//@Service 어노테이션이 없으면 스프링에서 "순수한 자바 클래스 파일"인 MemberService를 인식하지 못한다.
//따라서 어노테이션이 필요하다.


public class MemberService {
    private final MemberRepository memberRepository;

    // 외부에서 이렇게 값을 받아서 동작하게 하는 것을 D.I(의존성 주입)라고 한다.
    // 이렇게 하는 이유는 테스트 코드에서 같은 memberRepository를 쓰기 위함이라고 한다.

    // 동재 말로는 MemberRepository (인터페이스) 라고 써도 자동으로 구현체가 딸려온다고 한다.
    // 그래서 같은 인터페이스에 대해 구현체가 바뀌어도 유지보수가 굉장히 편리하다고 한다.

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long join(Member member) {
        // 같은 이름을 가지는 회원은 가입할 수 없다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // ctrl + alt + m으로 extract method 기능을 실행할 수 있다.
    // 메서드화 시키고싶은 부분을 드래그한 뒤 단축키를 이용해서 함수로 뺄 수 있다.
    // 당연히 이 클래스 안에서만 사용하니 private로 설정하는 것이 바람직하다.
    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        // result가 Optional 타입이라서 가능한 코드.
        // ifPresent는 값이 존재하면 실행되는 메서드로 Optional에서만 사용할 수 있다.
        // 자매품으로는 result.orElseGet()이 있다.
        // 또한 굳이 result로 저장 안하고 memberRepository.findByName(member.getName())에서 바로 사용해도 된다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(long memberId) {
        return memberRepository.findById(memberId);
    }
}
