package org.example.seed.event.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.seed.domain.Client;
import org.example.seed.event.UpdateEvent;
import org.example.seed.group.client.ClientUpdateGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by PINA on 22/05/2017.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientEvent extends UpdateEvent {

    @Valid
    @NotNull(groups = {ClientUpdateGroup.class})
    private Client client;
}
