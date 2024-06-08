package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@EnableWebFlux
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    public WebClient client() {
        return WebClient.create("http://localhost:8081/get_stream");
    }

    @Bean
    public Object request(WebClient client) {
        new Thread(() -> {
            client
                    .get()
                    .accept(MediaType.TEXT_EVENT_STREAM)
                    .retrieve()
                    .bodyToFlux(String.class)
                    .log()
                    .doOnNext(q -> log.info("{}", q))
                    .subscribe();
        }).run();

        return new Object();
    }
}
