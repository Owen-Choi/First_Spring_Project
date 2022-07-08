package hello.First_Spring_Project.service;

import hello.First_Spring_Project.repository.JpaMemberRepository;
import hello.First_Spring_Project.repository.MemberRepository;
import hello.First_Spring_Project.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 현재 데이터베이스 저장소가 정해지지 않았고 나중에 바뀔 수 있는 상황인데,\
// 이런 상황에서 데이터베이스 저장소가 정해지면 메인코드를 건들지 않고 이 설정파일만 수정해도 된다.
// 그게 우리가 수동으로 스프링 빈을 설정하는 이유이다.

@Configuration
public class SpringConfig {
    private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
}
