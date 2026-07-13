package in.demo;

import in.demo.service.DemoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig is getting read");
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public DemoService demoService() {
        return new DemoService();
    }

}
