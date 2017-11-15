package org.example.seed.service;

import org.example.seed.event.client.*;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by PINA on 16/06/2017.
 */
public interface ClientService {

  ListenableFuture<CatalogClientEvent> requestAllClients(final RequestAllClientEvent event);

  ListenableFuture<ResponseClientEvent> createClient(final CreateClientEvent event);

  ListenableFuture<ResponseClientEvent> requestClient(final RequestClientEvent event);

  ListenableFuture<ResponseClientEvent> updateClient(final UpdateClientEvent event);

  ListenableFuture<ResponseClientEvent> deleteClient(final DeleteClientEvent event);
}
