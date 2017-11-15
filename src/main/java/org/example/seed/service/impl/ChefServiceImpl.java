package org.example.seed.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.example.seed.catalog.ChefStatus;
import org.example.seed.domain.Chef;
import org.example.seed.domain.Telephone;
import org.example.seed.event.chef.*;
import org.example.seed.mapper.AccountMapper;
import org.example.seed.mapper.ChefMapper;
import org.example.seed.mapper.TelephoneMapper;
import org.example.seed.service.ChefService;
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
 * Created by PINA on 15/06/2017.
 */
@Service
public class ChefServiceImpl implements ChefService {

  private final ChefMapper chefMapper;
  private final AccountMapper accountMapper;
  private final TelephoneMapper telephoneMapper;

  @Autowired
  public ChefServiceImpl(
    final ChefMapper chefMapper, final AccountMapper accountMapper, final TelephoneMapper telephoneMapper
  ) {
    this.chefMapper = chefMapper;
    this.accountMapper = accountMapper;
    this.telephoneMapper = telephoneMapper;
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
  public ListenableFuture<CatalogChefEvent> requestAllChefs(final RequestAllChefEvent event) {

    final int offset = (event.getPage() - 1) * event.getLimit();
    final int limit = event.getPage() * event.getLimit();

    final Set<Chef> chefs = this.chefMapper.findMany(new RowBounds(offset, limit));
    final long total = this.chefMapper.count();

    return new AsyncResult<>(CatalogChefEvent.builder().chefs(chefs).total(total).build());
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public ListenableFuture<ResponseChefEvent> createChef(final CreateChefEvent event) {

    event.getChef().setStatus(ChefStatus.PRE_REGISTERED);
    event.getChef().setRating(0F);

    this.accountMapper.create(event);
    this.chefMapper.create(event);

    return new AsyncResult<>(null);
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
  public ListenableFuture<ResponseChefEvent> requestChef(final RequestChefEvent event) {

    return new AsyncResult<>(ResponseChefEvent.builder()
      .chef(this.chefMapper.findOne(event))
      .build());
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public ListenableFuture<ResponseChefEvent> updateChef(final UpdateChefEvent event) {

    return Optional.of(this.chefMapper.findAccountUUID(event.getChef().getId()))
      .map(id -> {
        final Set<Telephone> telephones = this.telephoneMapper
          .findManyByChef(event.getChef().getId());

        event.getChef().getTelephones()
          .parallelStream()
          .filter(t -> telephones
            .parallelStream()
            .noneMatch(s -> t.getNumber().equals(s.getNumber())))
          .forEach(t -> this.telephoneMapper.create(t, event.getChef().getId()));

        event.getChef().getTelephones()
          .parallelStream()
          .filter(t -> telephones
            .parallelStream()
            .anyMatch(s -> t.getNumber().equals(s.getNumber())))
          .forEach(t -> this.telephoneMapper.update(t, event.getChef().getId()));

        this.accountMapper.update(event);
        this.chefMapper.update(event);

        return new AsyncResult<>(ResponseChefEvent.builder().chef(null).build());
      })
      .orElseThrow(RuntimeException::new);
  }

  @Override
  @Async
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public ListenableFuture<ResponseChefEvent> deleteChef(final DeleteChefEvent event) {

    return Optional.of(this.chefMapper.findAccountUUID(event.getId()))
      .map(id -> {
        this.chefMapper.delete(event);
        this.accountMapper.delete(id);
        this.telephoneMapper.findManyByChef(event.getId())
          .parallelStream()
          .forEach(t -> this.telephoneMapper.delete(t.getId()));

        return new AsyncResult<>(ResponseChefEvent.builder().chef(null).build());
      })
      .orElseThrow(RuntimeException::new);
  }
}
