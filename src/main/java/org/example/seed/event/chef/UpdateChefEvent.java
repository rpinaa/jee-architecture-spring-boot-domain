package org.example.seed.event.chef;

import lombok.*;
import org.example.seed.domain.Chef;
import org.example.seed.event.UpdateEvent;

/**
 * Created by PINA on 22/05/2017.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateChefEvent extends UpdateEvent {
    private Chef chef;
}
