package org.example.seed.rest.impl;

import org.example.seed.event.client.*;
import org.example.seed.group.client.ClientCreateGroup;
import org.example.seed.group.client.ClientUpdateGroup;
import org.example.seed.rest.ClientRest;
import org.example.seed.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by PINA on 16/06/2017.
 */
@RestController
public class ClientRestImpl implements ClientRest {

    @Autowired
    private ClientService clientService;

    @Override
    public Callable<CatalogClientEvent> getAllClients(@RequestParam("page") final int page, @RequestParam("limit") final int limit)
            throws ExecutionException, InterruptedException {

        final RequestAllClientEvent requestAllClientEvent = RequestAllClientEvent.builder()
                .page(page)
                .limit(limit)
                .build();

        final CatalogClientEvent catalogClientEvent = this.clientService.requestAllClients(requestAllClientEvent).get();

        return () -> catalogClientEvent;
    }

    @Override
    public Callable<ResponseClientEvent> createClient(@RequestBody @Validated(value = {ClientCreateGroup.class}) final CreateClientEvent event)
            throws ExecutionException, InterruptedException {

        final ResponseClientEvent responseClientEvent = this.clientService.createClient(event).get();

        return () -> responseClientEvent;
    }

    @Override
    public Callable<ResponseClientEvent> getClient(@PathVariable("id") final UUID id)
            throws ExecutionException, InterruptedException {

        final RequestClientEvent requestClientEvent = RequestClientEvent.builder().id(id).build();

        final ResponseClientEvent responseClientEvent = this.clientService.requestClient(requestClientEvent).get();

        return () -> responseClientEvent;
    }

    @Override
    public Callable<ResponseClientEvent> updateClient(@RequestBody @Validated(value = {ClientUpdateGroup.class}) final UpdateClientEvent event)
            throws ExecutionException, InterruptedException {

        final ResponseClientEvent responseClientEvent = this.clientService.updateClient(event).get();

        return () -> responseClientEvent;
    }

    @Override
    public Callable<ResponseClientEvent> deleteClient(@PathVariable("id") final UUID id)
            throws ExecutionException, InterruptedException {

        final DeleteClientEvent deleteClientEvent = DeleteClientEvent.builder().id(id).build();

        final ResponseClientEvent responseClientEvent = this.clientService.deleteClient(deleteClientEvent).get();

        return () -> responseClientEvent;
    }
}
