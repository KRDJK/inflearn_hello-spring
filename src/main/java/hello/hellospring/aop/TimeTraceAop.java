package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP(Aspect Oriented Programming : 관점 지향 프로그래밍)로 적용하기 위해 필요한 어노테이션
@Component // 스프링은 프록시 방식의 AOP
public class TimeTraceAop { // 공통 관심사항 - 시간 측정

    @Around("execution(* hello.hellospring..*(..))") // 루트 패키지의 하위의 모든 메서드에 적용
                                            //.service..*(..)) 으로 서비스와 서비스 아래의 코드에서만 작동하게 할 수도 있다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();

        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
