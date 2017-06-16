package org.example.seed.event.account;

import lombok.Builder;
import org.example.seed.event.RequestEvent;

import java.util.UUID;

/**
 * Created by PINA on 22/05/2017.
 */
public class RequestAccountEvent extends RequestEvent {

    @Builder
    public RequestAccountEvent(final UUID id) {
        super(id);
    }
}
