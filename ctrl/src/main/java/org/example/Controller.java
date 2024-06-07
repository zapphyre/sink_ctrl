package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final String cmd = "{\"symbol\":\"tBTCUST\",\"channel\":\"ticker\",\"event\":\"subscribe\"}";
    private final StreamServrice streamServrice;

    @GetMapping(value = "get_stream", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getMeAstream() {
        return streamServrice.stream(cmd);
    }
}
