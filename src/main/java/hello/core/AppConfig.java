package hello.core;

import hello.core.discount.DiscountPollicy;
import hello.core.discount.FixDiscountPollicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepositry;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepositry memberRepository() {
        return new MemoryMemberRepositry();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPollicy());
    }

    public DiscountPollicy discountPollicy() {
//        return new FixDiscountPollicy();
        return new RateDiscountPolicy();
    }
}
