package org.example.seed.event.client;

import lombok.Builder;
import org.example.seed.event.RequestEvent;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Created by PINA on 22/05/2017.
 */
@XmlRootElement
public class RequestClientEvent extends RequestEvent {

    @Builder
    public RequestClientEvent(final UUID id) {
        super(id);
    }
}
