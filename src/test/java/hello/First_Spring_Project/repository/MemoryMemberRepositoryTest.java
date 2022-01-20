package hello.First_Spring_Project.repository;

import hello.First_Spring_Project.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
        MemoryMemberRepository repository = new MemoryMemberRepository();

        // 테스트 코드가 연속적으로 실행되면 미리 존재하던 데이터에 의해 오류가 발생하는 것을 막기 위한 코드이다.
        // AfterEach 어노테이션이 각 테스트코드가 끝날 때 마다 호출되어서 clearStore 함수를 실행하여 데이터를 없앤다.
        @AfterEach
        public void afterEach() {
            repository.clearStore();
        }

        @Test
        public void save() {
            Member member = new Member();
            member.setName("spring");
            repository.save(member);
            Member result = repository.findById(member.getId()).get();
            // findById의 반환 타입이 Optional이기 때문에 get을 이용해서 값을 꺼내도록 한다.
            Assertions.assertEquals(member, result);
            // 반환 값이랑 결과 값이 같은지 검증하기 위한 코드.
            // 두 값이 다르다면 실행 중 빨간 경고 발생, 같다면 초록색 점등과 함께 아무런 결과도 출력되지 않음
        }

        @Test
        public void findByName() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            Member result = repository.findByName("spring1").get();
            Assertions.assertEquals(member1, result);
        }

        @Test
        public void findAll() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            List<Member> result = repository.findAll();
            Assertions.assertEquals(result.size(), 2);
        }

        // 이와 같은 Test 코드를 먼저 개발해놓고 핵심 함수를 이 테스트코드에 맞춰서 개발하는
        // 방식을 테스트 주도 개발이라고 한다.
}
