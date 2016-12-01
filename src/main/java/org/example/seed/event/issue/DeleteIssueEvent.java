package org.example.seed.event.issue;

import lombok.Builder;
import org.example.seed.event.DeleteEvent;

/**
 * Created by Ricardo Pina Arellano on 30/11/2016.
 */
public class DeleteIssueEvent extends DeleteEvent {

    @Builder
    public DeleteIssueEvent(final Long id) {
        super(id);
    }
}
