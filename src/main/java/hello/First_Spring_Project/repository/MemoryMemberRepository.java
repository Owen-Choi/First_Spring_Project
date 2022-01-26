package hello.First_Spring_Project.repository;

import hello.First_Spring_Project.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// MemberService와 마찬가지로 스프링에서 인식하기 위해 @Repository 어노테이션을 달아준다.
@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(store.get(id));
        // 이렇게 null이 될 가능성이 있는 항목을 Optional로 감싸서 반환하면
        // client 측에서 조치를 취할 수 있다고 한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 테스트 코드의 오류 방지를 위한 콜백 메서드
    // 메서드 하나하나의 테스트코드가 종료될 때 마다 해당 메서드를 불러서
    // 저장된 데이터를 삭제해준다.
    public void clearStore() {
        store.clear();
    }
}
