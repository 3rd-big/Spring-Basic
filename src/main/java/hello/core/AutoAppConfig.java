package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepositry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/**
 * @ComponentScan
 * @Component 가 붙은 모든 클래스를 스프링 빈으로 등록
 *              이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용
 *              만약, 스프링 빈의 이름을 직접 지정하고 싶으면 @Component("memberService2") 처럼 사용
 */
@ComponentScan(
        // 탐색할 패키지의 시작 위치를 지정. 이 패키지를 포함해서 하위 패키지를 모두 탐색
        // basePackages = "hello.core.member, hello.core.order", 처럼 여러 시작 위치를 지정 할 수 있음
        basePackages = "hello.core.member",
        // AutoAppConfig.class 의 패키지(현재는 hello.core) 부터 찾음
        // default는 @ComponentScan 이 속한 패키지(현재는 hello.core) 부터 찾음
        basePackageClasses = AutoAppConfig.class,
        // excludeFilters -> @Component가 붙은 애들을 등록시킬 때 제외할 목록 => 결국 @Configuration 붙은 애들을 제외시킴
        // @Configuration도 F4눌러보면 @Conponent가 붙어있음..
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
