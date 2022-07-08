package hello.First_Spring_Project.repository;

import hello.First_Spring_Project.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 이렇게 해두면 구현체 만들 필요 없다.
    // SpringDataJpaMemberRepository 가 JpaRepository를 받고 있으면(구현하고 있으면) 구현체를 자동으로 만들고,
    // 스프링 빈에 자동으로 등록해준다고 한다.

    // 이렇게 해두고 @Configuration이 붙은 SpringConfig파일에서 memberRepository와 생성자만 만들어두면 된다.
    @Override
    Optional<Member> findByName(String name);
    // JPA에는 네이밍 규칙이 있다.
    // findByName(String name) 이라는 이름은
    // JPQL "select m from Member m where m.name = ?" 와 동일하다.
    // 그렇다면 findByNameAndId(String name, Long id) 라는 이름은
    // JPQL "select m from Member m where m.name = ? and m.id = ?" 와 동일하다고 한다!
    // 인터페이스 이름 만으로도 개발이 끝난다.
}
