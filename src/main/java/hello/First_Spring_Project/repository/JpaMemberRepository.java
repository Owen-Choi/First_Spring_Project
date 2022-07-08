package hello.First_Spring_Project.repository;

import hello.First_Spring_Project.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA는 엔티티 매니저를 통해서 모든 것이 동작한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist는 영속적으로 저장한다는 의미. jpa에서 데이터 저장에 사용된다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        // pk 기반으로 하나를 집어서 가져오는 쿼리는 jpa가 다 짜준다.
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // pk기반이 아니라서 리스트로 결과가 나올 수 있는 항목에 대해서는 쿼리를 짜줘야 한다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
