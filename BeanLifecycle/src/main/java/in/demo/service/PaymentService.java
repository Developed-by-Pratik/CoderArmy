package in.demo.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PaymentService implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    public PaymentService() {
        System.out.println("PaymentService is created");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanName is : " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Application Context is : " + applicationContext.getClass().getSimpleName());
    }

    @PostConstruct
    public void initializeOrder() {
        System.out.println("Initializing the PaymentService");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing the PaymentService using afterPropertiesSet()");
    }

    @PreDestroy
    public void destroyOrder() {
        System.out.println("Destroy the PaymentService");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy the PaymentService using destroy()");
    }

}
