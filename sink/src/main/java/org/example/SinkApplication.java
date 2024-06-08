package org.example;

import jakarta.websocket.Session;
import org.example.sink.WebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Sinks;

@SpringBootApplication
public class SinkApplication {
    final Sinks.Many<String> sink = Sinks.many().multicast().directAllOrNothing();

    public static void main(String[] args) {
        SpringApplication.run(SinkApplication.class, args);
    }

    @Bean
    public WebSocket createSocket() {
        return new WebSocket(sink);
    }

    @Bean
    public Sinks.Many getSink() {
        return sink;
    }

    @Bean
    public Session connect(WebSocket webSocket) {
        return webSocket.connect("wss://api.bitfinex.com/ws/2");
    }
}