package org.example.seed.domain;

import lombok.Data;
import org.example.seed.group.chef.ChefCreateGroup;
import org.example.seed.group.chef.ChefUpdateGroup;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by PINA on 14/06/2017.
 */
@Data
public class Account {

    public Account() {
        this.id = UUID.randomUUID();
    }

    @NotNull(groups = {ChefUpdateGroup.class})
    private UUID id;

    @Email(groups = {ChefCreateGroup.class, ChefUpdateGroup.class})
    @Size(max = 45, groups = {ChefCreateGroup.class, ChefUpdateGroup.class})
    @NotNull(groups = {ChefCreateGroup.class, ChefUpdateGroup.class})
    private String email;

    @Size(min = 2, max = 80, groups = {ChefCreateGroup.class, ChefUpdateGroup.class})
    @NotNull(groups = {ChefCreateGroup.class, ChefUpdateGroup.class})
    private String firstName;

    @Size(min = 2, max = 80, groups = {ChefCreateGroup.class, ChefUpdateGroup.class})
    @NotNull(groups = {ChefCreateGroup.class, ChefUpdateGroup.class})
    private String lastName;
}
