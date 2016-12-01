package org.example.seed.event.issue;

import lombok.Builder;
import org.example.seed.event.RequestEvent;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
public class RequestIssueEvent extends RequestEvent {

    @Builder
    public RequestIssueEvent(final Long id) {
        super(id);
    }
}
