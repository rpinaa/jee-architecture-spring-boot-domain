package org.example.seed.domain;

import lombok.Data;
import org.example.seed.catalog.TelephoneType;
import org.example.seed.group.chef.ChefUpdateGroup;
import org.example.seed.group.client.ClientCreateGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Created by PINA on 14/06/2017.
 */
@Data
@XmlRootElement
public class Telephone {

    public Telephone () {
        this.id = UUID.randomUUID();
    }

    private UUID id;

    @Size(min = 2, max = 15)
    @NotNull(groups = {ClientCreateGroup.class, ChefUpdateGroup.class})
    private String name;

    @Size(min = 5, max = 12)
    @NotNull(groups = {ClientCreateGroup.class, ChefUpdateGroup.class})
    private String number;

    @Size(min = 2, max = 10)
    @NotNull(groups = {ClientCreateGroup.class, ChefUpdateGroup.class})
    private String lada;

    @NotNull(groups = {ClientCreateGroup.class, ChefUpdateGroup.class})
    private TelephoneType type;
}
