package hello.First_Spring_Project.controller;

public class MemberForm {
    // 이 변수에 html의 name부분에 해당하는 값이 들어오게 된다.
    // 변수가 private라 막 접근을 못하기 때문에 스프링에서 setName을 호출해서 자동으로 넣어준다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
