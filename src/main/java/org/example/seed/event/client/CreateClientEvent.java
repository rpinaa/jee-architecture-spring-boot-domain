package org.example.seed.event.client;

import lombok.*;
import org.example.seed.domain.Client;
import org.example.seed.event.CreateEvent;
import org.example.seed.group.client.ClientCreateGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by PINA on 22/05/2017.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement
public class CreateClientEvent extends CreateEvent {

    @Valid
    @NotNull(groups = {ClientCreateGroup.class})
    private Client client;
}
