package org.example.seed.event.chef;

import lombok.Builder;
import org.example.seed.event.DeleteEvent;

import java.util.UUID;

/**
 * Created by PINA on 22/05/2017.
 */
public class DeleteChefEvent extends DeleteEvent {

    @Builder
    public DeleteChefEvent(final UUID id) {
        super(id);
    }
}
