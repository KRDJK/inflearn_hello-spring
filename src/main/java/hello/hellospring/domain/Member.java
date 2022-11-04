package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 Entity가 된다.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 알아서 pk를 생성해주는 것을 identity 방식이라고 한다.
    private Long id;

//    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
