package in.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {

        System.out.println("Application Started...");

        System.out.println("Application context is getting created !\n");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("\nApplication context is created !\n");


        System.out.println("Closing the ApplicationContext");
        ((AnnotationConfigApplicationContext) context).close();


    }
}
