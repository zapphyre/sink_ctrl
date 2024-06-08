package org.example.sink;

import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class StreamServrice {

    private final Sinks.Many sink;
    private final Session session;

    public Flux<String> stream(String command) {
        session.getAsyncRemote()
                .sendText(command);
        Flux flux = sink.asFlux();

//        flux.log()
//                .subscribe();

        return flux;
    }
}
