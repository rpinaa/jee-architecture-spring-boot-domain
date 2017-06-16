package org.example.seed.event.chef;

import lombok.*;
import org.example.seed.domain.Chef;
import org.example.seed.event.UpdateEvent;
import org.example.seed.group.chef.ChefUpdateGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by PINA on 22/05/2017.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateChefEvent extends UpdateEvent {

    @Valid
    @NotNull(groups = {ChefUpdateGroup.class})
    private Chef chef;
}
