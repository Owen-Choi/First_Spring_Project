package hello.First_Spring_Project.service;

import hello.First_Spring_Project.repository.JpaMemberRepository;
import hello.First_Spring_Project.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 현재 데이터베이스 저장소가 정해지지 않았고 나중에 바뀔 수 있는 상황인데,\
// 이런 상황에서 데이터베이스 저장소가 정해지면 메인코드를 건들지 않고 이 설정파일만 수정해도 된다.
// 그게 우리가 수동으로 스프링 빈을 설정하는 이유이다.

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    // 이렇게 Autowired를 붙여놓으면 스프링이 컨테이너에서 memberRepository를 찾는데,
    // 기존의 @Bean을 주석처리해서 현재 등록한 Bean이 없는 상황이다.
    // 그렇지만 Bean이 없는 것이 아니다. SpringDataJpaMemberRepository 인터페이스가 JpaRepository를 구현하고,
    // 이 JpaRepository가 (프록시를 이용해서) MemberRepository에 대한 구현체를 만들어내고, 이를 컨테이너에 등록한다.
    @Autowired  // 생성자가 하나라면 생략 가능. 잊지말자
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // SpringDataJpa가 자동으로 인터페이스의 구현체를 만들어주는 기능을 확인하기 위해
    // 기존 JPA를 위한 코드는 주석처리 해놓겠음
//    private DataSource dataSource;
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new JpaMemberRepository(em);
//    }
}
