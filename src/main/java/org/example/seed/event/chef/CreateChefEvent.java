package org.example.seed.event.chef;

import lombok.*;
import org.example.seed.domain.Chef;
import org.example.seed.event.CreateEvent;
import org.example.seed.group.chef.ChefCreateGroup;

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
public class CreateChefEvent extends CreateEvent {

    @Valid
    @NotNull(groups = {ChefCreateGroup.class})
    private Chef chef;
}
