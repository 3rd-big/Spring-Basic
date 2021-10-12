package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    /**
     * ObjectProvider 덕분에 'ObjectProvider.getObject()'를 호출하는 시점까지 request scope 빈의 생성을 지연 할 수 있음
     * 'ObjectProvider.getObject()'를 호출하는 시점에는 HTTP 요청이 진행중이므로 request scope 빈의 생성이 정상처리됨
     */
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
