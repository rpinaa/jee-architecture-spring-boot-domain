package org.example.seed.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.seed.catalog.ChefStatus;
import org.example.seed.constraint.Curp;
import org.example.seed.constraint.Rfc;
import org.example.seed.group.chef.ChefUpdateGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

/**
 * Created by PINA on 14/06/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Chef extends Dates {

    public Chef() {
        this.id = UUID.randomUUID();
    }

    @NotNull(groups = {ChefUpdateGroup.class})
    private UUID id;

    @Rfc(groups = {ChefUpdateGroup.class})
    @NotNull(groups = {ChefUpdateGroup.class})
    private String rfc;

    @Curp(groups = {ChefUpdateGroup.class})
    @NotNull(groups = {ChefUpdateGroup.class})
    private String curp;

    @Min(value = 0, groups = {ChefUpdateGroup.class})
    @Max(value = 5, groups = {ChefUpdateGroup.class})
    @NotNull(groups = {ChefUpdateGroup.class})
    private Float rating;

    @NotNull(groups = {ChefUpdateGroup.class})
    private ChefStatus status;

    @Valid
    private Account account;

    @Valid
    @NotEmpty(groups = {ChefUpdateGroup.class})
    private Set<Telephone> telephones;
}
