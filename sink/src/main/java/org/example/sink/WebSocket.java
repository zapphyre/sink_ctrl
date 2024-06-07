package org.example.sink;

import jakarta.websocket.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

import java.io.IOException;
import java.net.URI;

@Slf4j
@ClientEndpoint
@RequiredArgsConstructor
public class WebSocket {

    private final Sinks.Many<String> sink;
    private Session userSession;

    public Session connect(String uri) {
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try {
            return this.userSession = container.connectToServer(this, URI.create(uri));
        } catch (DeploymentException | IOException e) {
            log.error("error on connect: {}", e.getMessage());
        }

        return this.userSession;
    }

    @OnOpen
    public void onOpen(final Session userSession) {
        log.debug("now open");
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(final Session userSession, final CloseReason reason) {
        log.info("closing socket reason: {}", reason.getReasonPhrase());
    }

    @OnError
    public void onError(final Session session, final Throwable t) {
        log.info("socket error of: {}", t.getMessage());
    }

    @OnMessage(maxMessageSize = 1048576)
    public void onMessage(String message) {
        sink.tryEmitNext(message);
    }
}
