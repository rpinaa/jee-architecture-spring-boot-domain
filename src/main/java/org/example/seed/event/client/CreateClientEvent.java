package org.example.seed.event.client;

import lombok.*;
import org.example.seed.domain.Client;
import org.example.seed.event.CreateEvent;

/**
 * Created by PINA on 22/05/2017.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateClientEvent extends CreateEvent {
    private Client client;
}
