package hello.First_Spring_Project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP 클래스는 좀 특별취급을 받는다. @Component를 붙여서 스캔을 할 수도 있지만
// SpringConfig 클래스에서 수동으로 Bean 등록을 하는 것이 직관적이고 좋다고 한다.

// @Aspect 애노테이션이 있어야 AOP로서 사용할 수 있다.
@Aspect
@Component
public class TimeTraceAop {

    // "시간 측정" 이라는 공통 관심사를 어디에 쓸지 타겟팅을 해줄 수 있다.
    // execution의 경로가 다음과 같다면 First_Spring_project 패키지 하위의 것들에 모두 적용하겠다는 뜻.
    // 원리는 애플리케이션 내에서 메서드를 호출할 때마다 인터셉트가 걸려서 시간측정 로직을 수행할 수 있는 것.
    @Around("execution(* hello.First_Spring_Project..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // joinPoint.proceed 하면 다음 메서드로 진행이 된다고 한다.
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
            // 위 출력문의 결과는 다음과 같다.
            // END: execution(String hello.First_Spring_Project.controller.MemberController.list(Model)) 176ms
        }
    }
}
