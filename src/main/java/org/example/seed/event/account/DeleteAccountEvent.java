package org.example.seed.event.account;

import lombok.Builder;
import org.example.seed.event.DeleteEvent;

import java.util.UUID;

/**
 * Created by PINA on 22/05/2017.
 */
public class DeleteAccountEvent extends DeleteEvent {

    @Builder
    public DeleteAccountEvent(final UUID id) {
        super(id);
    }
}
