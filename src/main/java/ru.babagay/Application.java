package ru.babagay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.babagay.config.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        SpringApplication.run(new Class<?>[] {Application.class, JpaConfig.class}, args);
    }


}
