package org.example.seed.event.chef;

import lombok.*;
import org.example.seed.domain.Chef;
import org.example.seed.event.account.CreateAccountEvent;
import org.example.seed.group.chef.ChefCreateGroup;

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
public class CreateChefEvent extends CreateAccountEvent {

    @Valid
    @NotNull(groups = {ChefCreateGroup.class})
    private Chef chef;
}
