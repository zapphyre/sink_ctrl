package org.example.client;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class ClientService {

    WebClient.ResponseSpec responseSpec;

    @PostConstruct
    void init() {
        WebClient.create("http://localhost:8081/get_stream")
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(String.class)
                .log()
                .subscribe(q -> log.info("{}", q));
    }
}
