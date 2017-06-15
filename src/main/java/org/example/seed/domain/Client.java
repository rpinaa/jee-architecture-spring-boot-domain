package org.example.seed.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.seed.group.client.ClientCreateGroup;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
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
public class Client extends Dates {

    public Client () {
        this.id = UUID.randomUUID();
    }

    private UUID id;

    @Email
    @NotNull(groups = {ClientCreateGroup.class})
    private String email;

    @Size(min = 2, max = 80)
    @NotNull(groups = {ClientCreateGroup.class})
    private String firstName;

    @Size(min = 2, max = 80)
    @NotNull(groups = {ClientCreateGroup.class})
    private String lastName;

    @Valid
    private Telephone telephone;
}
