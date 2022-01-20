package hello.First_Spring_Project.repository;

import hello.First_Spring_Project.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(long id);
    Optional<Member> findByName(String name);
    // Optional은 처음보는 자료형이다. JAVA8에 새로 생긴 기능인데,
    // findById에서 id가 없으면 null을 반환하게 될텐데 요즘은 null을 바로 반환하지 않고
    // optional로 감싸서 처리하는 것을 선호한다고 한다.
    List<Member> findAll();
    // 서비스의 기능은 회원가입과 회원조회 단 두개만을 지원하기 때문에 인터페이스는 이 두가지 기능만을 위한 것으로
    // 구성이 된다.
}
