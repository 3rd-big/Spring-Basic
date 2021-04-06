package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepositry;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * @Bean memberService -> new MemoryREpository()
     * @Bean orderService -> new MemoryRepository()
     * 결과적으로 각각 다른 2개의 'MemoryMemberRepository' 가 생성되면서 싱글톤이 깨지는 것 처럼 보임
     * 스프링 컨테이너는 어떻게 해결하는지?
     */

    /**
     * 메소드이름(memberService)을 Key
     * 리턴되는 객체 인스턴스( new MemberServiceImpl(memberRepository()) )를 Value
     * Key, Value로 스프링 컨테이너에 등록
     * 꺼낼때는 메소드이름(Key)를 입력해서 사용
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepositry memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepositry();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
