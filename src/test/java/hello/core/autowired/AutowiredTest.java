package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 상황: Member는 스프링 빈이 아님!!

        // required = false (스프링 기본 옵션은 true)
        // 의존관계가 없으면 메서드 호출이 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // @Nullable
        // 호출은 되나, null로 들어옴
        @Autowired
        public void setNoBean2(@Nullable  Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // JAVA8 부터 제공된 옵션
        // Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
