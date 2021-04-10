package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * @Autowired 어디에 쓰이나?
     *
     * @ComponentScan 이 스캔 할 때
     * @Component 가 붙은 애들만 스캔하면서 빈 등록시킴
     * But, 아래 MemberServiceImpl 같은 경우 의존성 주입은 어떻게?
     *      -> @Autowired 로 해결(의존성 자동 주입)
     */
    @Autowired  // == ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.sava(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
