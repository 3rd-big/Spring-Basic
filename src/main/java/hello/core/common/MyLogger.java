package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request")
/**
 * CGLIB��� ���̺귯���� �� Ŭ������ ��� ���� ��¥ ���Ͻ� ��ü�� ���� ����
 * @Scope �� proxyMode = ScopedProxyMode.TARGET_CLASS �� �����ϸ� ������ �����̳ʴ� CGLIB��� ����Ʈ�ڵ带 �����ϴ�
 * ���̺귯���� ����ؼ�, MyLogger�� ��ӹ��� ��¥ ���Ͻ� ��ü�� ����
 * ����� Ȯ���غ��� �츮�� ����� ������ MyLogger Ŭ������ �ƴ϶� 'MyLogger$$EnhancerBySpringCGLIB'�̶�� Ŭ������ ������� ��ü�� ��� ��ϵǾ������� Ȯ�� ����
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
