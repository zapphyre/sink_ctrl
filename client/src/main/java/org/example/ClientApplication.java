package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

//    @Bean
    public WebClient.ResponseSpec request() {
        WebClient.ResponseSpec spec = WebClient.create("http://localhost:8081/get_stream")
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

//        spec.bodyToFlux(String.class)
//                .log()
//                .subscribe(q -> log.info("{}", q));

//                .bodyToFlux(String.class)
//                .log();

        return spec;
    }
}
