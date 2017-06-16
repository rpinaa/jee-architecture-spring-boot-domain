package org.example.seed.service;

import org.example.seed.event.client.*;

import java.util.concurrent.Future;

/**
 * Created by PINA on 16/06/2017.
 */
public interface ClientService {

    Future<CatalogClientEvent> requestAllClients(final RequestAllClientEvent clientEvent);

    Future<ResponseClientEvent> createClient(final CreateClientEvent clientEvent);

    Future<ResponseClientEvent> requestClient(final RequestClientEvent clientEvent);

    Future<ResponseClientEvent> updateClient(final UpdateClientEvent clientEvent);

    Future<ResponseClientEvent> deleteClient(final DeleteClientEvent clientEvent);
}
