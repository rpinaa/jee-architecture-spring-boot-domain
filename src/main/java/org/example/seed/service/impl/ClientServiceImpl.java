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
    public Future<CatalogClientEvent> requestAllClients(final RequestAllClientEvent event) {

        final int offset = (event.getPage() - 1) * event.getLimit();
        final int limit = event.getPage() * event.getLimit();

        final Set<Client> clients = this.clientMapper.findMany(new RowBounds(offset, limit));
        final long total = this.clientMapper.count();

        return new AsyncResult<>(CatalogClientEvent.builder().clients(clients).total(total).build());
    }

    @Override
    @Async
    @CacheEvict(value = "client", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseClientEvent> createClient(final CreateClientEvent event) {

        event.getClient().setStatus(ClientStatus.REGISTERED);
        event.getClient().setRating(0F);

        this.clientMapper.create(event);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @Cacheable(value = "client")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<ResponseClientEvent> requestClient(final RequestClientEvent event) {

        final Client client = this.clientMapper.findOne(event);

        return new AsyncResult<>(ResponseClientEvent.builder().client(client).build());
    }

    @Override
    @Async
    @CacheEvict(value = "client", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseClientEvent> updateClient(final UpdateClientEvent event) {

        this.clientMapper.update(event);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @CacheEvict(value = "client", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseClientEvent> deleteClient(final DeleteClientEvent event) {

        this.clientMapper.delete(event);

        return new AsyncResult<>(null);
    }
}
