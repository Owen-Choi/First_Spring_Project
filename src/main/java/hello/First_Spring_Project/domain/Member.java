package hello.First_Spring_Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// Entity 태그를 달면 이제부터 JPA가 관리하는 클래스라고 인식이 된다.
public class Member {
    // id가 pk이면서 자동으로 증가한다. 전마협에서 user_id 같은 맥락이다.
    // 이런 데이터를 IDENTITY라고 한다.
    // 이렇게 활용되는 데이터에 @Id, @GeneratedValue(strategy = GenerationType.IDENTITY)를 붙여준다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(name="username") 이라고 해두면 데이터베이스에서 username이라는 칼럼과 매핑이 된다.
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
