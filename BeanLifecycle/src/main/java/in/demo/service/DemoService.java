package in.demo.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class DemoService implements InitializingBean, DisposableBean {

    public DemoService() {
        System.out.println("DemoService is created");
    }

    @PostConstruct
    public void initializeOrder() {
        System.out.println("Initializing the DemoService");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing the DemoService using afterPropertiesSet()");
    }

    public void start() {
        System.out.println("Starting the DemoService");
    }

    @PreDestroy
    public void destroyOrder() {
        System.out.println("Destroy the DemoService");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy the DemoService using destroy()");
    }

    public void stop() {
        System.out.println("Stopping the DemoService");
    }

}
