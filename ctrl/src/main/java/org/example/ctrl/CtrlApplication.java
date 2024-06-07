package org.example.ctrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.example")
@SpringBootApplication
public class CtrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(CtrlApplication.class, args);
    }

}
