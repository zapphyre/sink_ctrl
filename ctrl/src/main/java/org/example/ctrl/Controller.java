package org.example.ctrl;

import lombok.RequiredArgsConstructor;
import org.example.sink.StreamServrice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final String cmd = "{\"symbol\":\"tBTCUST\",\"channel\":\"ticker\",\"event\":\"subscribe\"}";
    private final StreamServrice streamServrice;

    @GetMapping(value = "get_stream", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getMeAstream() {
//        return streamServrice.stream(cmd);
        return Flux.fromIterable(List.of(
                "qwer", "Qwr", "asdf", "zxcv",
                "qwer", "Qwr", "asdf", "zxcv",
                "qwer", "Qwr", "asdf", "zxcv"
        ));
    }
}
