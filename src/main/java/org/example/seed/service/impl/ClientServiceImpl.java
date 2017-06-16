package org.example.seed.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.example.seed.catalog.ClientStatus;
import org.example.seed.domain.Client;
import org.example.seed.event.client.*;
import org.example.seed.mapper.ClientMapper;
import org.example.seed.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.Future;

/**
 * Created by PINA on 16/06/2017.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    @Async
    @Cacheable(value = "client")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<CatalogClientEvent> requestAllClients(final RequestAllClientEvent clientEvent) {

        final int offset = (clientEvent.getPage() - 1) * clientEvent.getLimit();
        final int limit = clientEvent.getPage() * clientEvent.getLimit();

        final Set<Client> clients = this.clientMapper.findClients(new RowBounds(offset, limit));
        final long total = this.clientMapper.countClients();

        return new AsyncResult<>(CatalogClientEvent.builder().clients(clients).total(total).build());
    }

    @Override
    @Async
    @CacheEvict(value = "client", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseClientEvent> createClient(final CreateClientEvent clientEvent) {

        clientEvent.getClient().setStatus(ClientStatus.REGISTERED);
        clientEvent.getClient().setRating(0F);

        this.clientMapper.createClient(clientEvent);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @Cacheable(value = "client")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<ResponseClientEvent> requestClient(final RequestClientEvent clientEvent) {

        final Client client = this.clientMapper.findClient(clientEvent);

        return new AsyncResult<>(ResponseClientEvent.builder().client(client).build());
    }

    @Override
    @Async
    @CacheEvict(value = "client", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseClientEvent> updateClient(final UpdateClientEvent clientEvent) {

        this.clientMapper.updateClient(clientEvent);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @CacheEvict(value = "client", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseClientEvent> deleteClient(final DeleteClientEvent clientEvent) {

        this.clientMapper.deleteClient(clientEvent);

        return new AsyncResult<>(null);
    }
}
