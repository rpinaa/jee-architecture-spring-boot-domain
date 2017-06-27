package org.example.seed.event.chef;

import lombok.*;
import org.example.seed.domain.Chef;
import org.example.seed.event.account.UpdateAccountEvent;
import org.example.seed.group.chef.ChefUpdateGroup;

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
public class UpdateChefEvent extends UpdateAccountEvent {

    @Valid
    @NotNull(groups = {ChefUpdateGroup.class})
    private Chef chef;
}
