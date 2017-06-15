package org.example.seed.event.client;

import lombok.Builder;
import org.example.seed.event.RequestEvent;

import java.util.UUID;

/**
 * Created by PINA on 22/05/2017.
 */
public class RequestClientEvent extends RequestEvent {

    @Builder
    public RequestClientEvent(final UUID id) {
        super(id);
    }
}
