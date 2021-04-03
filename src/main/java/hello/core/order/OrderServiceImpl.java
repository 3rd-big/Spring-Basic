package hello.core.order;

import hello.core.discount.DiscountPollicy;
import hello.core.discount.FixDiscountPollicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepositry;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private DiscountPollicy discountPollicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPollicy discountPollicy) {
        this.memberRepository = memberRepository;
        this.discountPollicy = discountPollicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPollicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
