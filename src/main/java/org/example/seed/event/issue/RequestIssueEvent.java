package org.example.seed.event.issue;

import lombok.Builder;
import org.example.seed.event.RequestEvent;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
@XmlRootElement
public class RequestIssueEvent extends RequestEvent {

    @Builder
    public RequestIssueEvent(final UUID id) {
        super(id);
    }
}
