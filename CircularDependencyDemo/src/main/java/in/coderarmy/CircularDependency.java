package in.coderarmy;

import in.coderarmy.simple.A;
import in.coderarmy.simple.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CircularDependency {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService order = context.getBean(OrderService.class);
        order.placeOrder();

//      A a = new A(new B());  // Circular Dependency Problem
//      A a = new A();         // StackOverflow Error

    }

}
