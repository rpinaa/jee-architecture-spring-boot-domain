package org.example.seed.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.example.seed.catalog.ClientStatus;
import org.example.seed.domain.Client;
import org.example.seed.event.client.*;
import org.example.seed.mapper.ClientMapper;
import org.example.seed.mapper.TelephoneMapper;
import org.example.seed.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;
import java.util.Set;

/**
 * Created by PINA on 16/06/2017.
 */
@Service
public class ClientServiceImpl implements ClientService {

  private final ClientMapper clientMapper;
  private final TelephoneMapper telephoneMapper;

  @Autowired
  public ClientServiceImpl(final ClientMapper clientMapper, final TelephoneMapper telephoneMapper) {
    this.clientMapper = clientMapper;
    this.telephoneMapper = telephoneMapper;
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
  public ListenableFuture<CatalogClientEvent> requestAllClients(final RequestAllClientEvent event) {

    final int offset = (event.getPage() - 1) * event.getLimit();
    final int limit = event.getPage() * event.getLimit();

    final Set<Client> clients = this.clientMapper.findMany(new RowBounds(offset, limit));
    final long total = this.clientMapper.count();

    return new AsyncResult<>(CatalogClientEvent.builder().clients(clients).total(total).build());
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public ListenableFuture<ResponseClientEvent> createClient(final CreateClientEvent event) {

    event.getClient().setStatus(ClientStatus.REGISTERED);
    event.getClient().setRating(0F);

    this.clientMapper.create(event);
    this.telephoneMapper.create(event.getClient().getTelephone(), null);

    return new AsyncResult<>(null);
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
  public ListenableFuture<ResponseClientEvent> requestClient(final RequestClientEvent event) {

    return new AsyncResult<>(ResponseClientEvent.builder()
      .client(this.clientMapper.findOne(event))
      .build());
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public ListenableFuture<ResponseClientEvent> updateClient(final UpdateClientEvent event) {

    this.clientMapper.update(event);
    this.telephoneMapper.update(event.getClient().getTelephone(), null);

    return new AsyncResult<>(null);
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public ListenableFuture<ResponseClientEvent> deleteClient(final DeleteClientEvent event) {

    return Optional.of(this.clientMapper.findTelephoneUUID(event.getId()))
      .map(id -> {
        this.telephoneMapper.delete(id);
        this.clientMapper.delete(event);

        return new AsyncResult<>(ResponseClientEvent.builder().client(null).build());
      })
      .orElseThrow(RuntimeException::new);
  }
}
