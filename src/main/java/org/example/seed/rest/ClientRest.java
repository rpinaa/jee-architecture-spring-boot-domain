package org.example.seed.rest;

import org.example.seed.event.client.*;
import org.example.seed.group.client.ClientCreateGroup;
import org.example.seed.group.client.ClientUpdateGroup;
import org.example.seed.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by PINA on 16/06/2017.
 */
@RestController
@RequestMapping(path = ClientRest.CLIENT_ROOT_PATH)
public class ClientRest {

  public static final String CLIENT_ROOT_PATH =  "/clients";
  public static final String CLIENT_CRUD_PATH =  "/{clientId}";
  public static final String ORDER_CRUD_PATH =  "/{clientId}/orders";

  private final ClientService clientService;

  @Autowired
  public ClientRest(final ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Callable<CatalogClientEvent> getAllClients(
    @RequestParam("page") final int page, @RequestParam("limit") final int limit
  )
    throws ExecutionException, InterruptedException {

    final RequestAllClientEvent requestAllClientEvent = RequestAllClientEvent.builder()
      .page(page)
      .limit(limit)
      .build();

    final CatalogClientEvent catalogClientEvent = this.clientService.requestAllClients(requestAllClientEvent).get();

    return () -> catalogClientEvent;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Callable<ResponseClientEvent> createClient(
    @RequestBody @Validated(value = {ClientCreateGroup.class}) final CreateClientEvent event
  )
    throws ExecutionException, InterruptedException {

    final ResponseClientEvent responseClientEvent = this.clientService.createClient(event).get();

    return () -> responseClientEvent;
  }

  @GetMapping(ClientRest.CLIENT_CRUD_PATH)
  @ResponseStatus(HttpStatus.OK)
  public Callable<ResponseClientEvent> getClient(@PathVariable("clientId") final UUID idClient)
    throws ExecutionException, InterruptedException {

    final RequestClientEvent requestClientEvent = RequestClientEvent.builder().id(idClient).build();

    final ResponseClientEvent responseClientEvent = this.clientService.requestClient(requestClientEvent).get();

    return () -> responseClientEvent;
  }

  @PutMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Callable<ResponseClientEvent> updateClient(
    @RequestBody @Validated(value = {ClientUpdateGroup.class}) final UpdateClientEvent event
  )
    throws ExecutionException, InterruptedException {

    final ResponseClientEvent responseClientEvent = this.clientService.updateClient(event).get();

    return () -> responseClientEvent;
  }

  @DeleteMapping(ClientRest.CLIENT_CRUD_PATH)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Callable<ResponseClientEvent> deleteClient(@PathVariable("clientId") final UUID idClient)
    throws ExecutionException, InterruptedException {

    final DeleteClientEvent deleteClientEvent = DeleteClientEvent.builder().id(idClient).build();

    final ResponseClientEvent responseClientEvent = this.clientService.deleteClient(deleteClientEvent).get();

    return () -> responseClientEvent;
  }
}
