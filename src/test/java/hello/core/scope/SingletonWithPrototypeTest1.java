package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = applicationContext.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = applicationContext.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {
        
        // 생성시점에 주입
//        private final PrototypeBean prototypeBean;

        /**
         * ObjectFactory: 기능이 단순, 별도의 라이브러리 필요 없음. 스프링에 의존
         * ObjectProvider: ObjectFactory 상속, 옵션, 스트림 처리 등 편의기능이 많고, 별도의 라이브러리 필요 없음. 스프링에 의존

            @Autowired
            private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
         */
//        @Autowired
//        ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {

            /**
             * 실행해보면 'prototypeBeanObjectProvider.getObject()'을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 알 수있음
             * 'ObjectProvider'의 'getObject()'를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환(DL)
             * 스프링이 제공하는 기능을 사용하지만, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 쉬움
             */
            // PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();


            /**
             * 실행해보면 'provider.get()' 을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있음
             * 'provider'의 'get()'을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환(DL)
             * 자바 표준이고, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 훨씬 쉬움
             * 'Provider'는 지금 딱 필요한 DL 정도의 기능만 제공
             */
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count += 1;
        }

        public int getCount() {
            return  count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
