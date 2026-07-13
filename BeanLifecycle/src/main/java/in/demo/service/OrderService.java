package in.demo.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class OrderService implements InitializingBean, DisposableBean {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        System.out.println("Injecting the PaymentService Dependency for OrderService");
        this.paymentService = paymentService;
        System.out.println("Dependency Injected");
        System.out.println("OrderService is created");
    }

    @PostConstruct
    public void initializeOrder() {
        System.out.println("Initializing the OrderService");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing the OrderService using afterPropertiesSet()");
    }

    @PreDestroy
    public void destroyOrder() {
        System.out.println("Destroy the OrderService");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy the OrderService using destroy()");
    }
}
