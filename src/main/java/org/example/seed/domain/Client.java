package org.example.seed.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.seed.catalog.ClientStatus;
import org.example.seed.group.client.ClientCreateGroup;
import org.example.seed.group.client.ClientUpdateGroup;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by PINA on 14/06/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Client extends Dates {

  public Client() {
    this.id = UUID.randomUUID();
  }

  @NotNull(groups = {ClientUpdateGroup.class})
  private UUID id;

  @Email(groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  @NotNull(groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  private String email;

  @Size(min = 2, max = 80, groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  @NotNull(groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  private String firstName;

  @Size(min = 2, max = 80, groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  @NotNull(groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  private String lastName;

  @Min(value = 0, groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  @Max(value = 5, groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  @NotNull(groups = {ClientCreateGroup.class, ClientUpdateGroup.class})
  private Float rating;

  @NotNull(groups = {ClientUpdateGroup.class})
  private ClientStatus status;

  @Valid
  private Telephone telephone;
}
