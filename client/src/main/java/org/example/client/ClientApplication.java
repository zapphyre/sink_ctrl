package org.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

@Slf4j
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    public Flux<String> request() {
        Flux<String> stringFlux = WebClient.create("http://localhost:8081/get_stream")
                .get()
                .retrieve()
                .bodyToFlux(String.class)
                .log();

        stringFlux.subscribe(q ->
                System.out.println(q));

        return stringFlux;
    }
}
