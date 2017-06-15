package org.example.seed.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.seed.group.chef.ChefCreateGroup;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Created by PINA on 14/06/2017.
 */
@Data
@XmlRootElement
@EqualsAndHashCode(callSuper = true)
public class Account extends Dates {

    public Account () {
        this.id = UUID.randomUUID();
    }

    private UUID id;

    @Email
    @NotNull(groups = {ChefCreateGroup.class})
    private String email;

    @Size(min = 2, max = 80)
    @NotNull(groups = {ChefCreateGroup.class})
    private String firstName;

    @Size(min = 2, max = 80)
    @NotNull(groups = {ChefCreateGroup.class})
    private String lastName;
}
