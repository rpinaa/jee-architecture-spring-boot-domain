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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * Created by PINA on 15/06/2017.
 */
@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ChefMapper chefMapper;

    @Autowired
    private TelephoneMapper telephoneMapper;

    @Override
    @Async
    @Cacheable(value = "chefs")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<CatalogChefEvent> requestAllChefs(final RequestAllChefEvent event) {

        final int offset = (event.getPage() - 1) * event.getLimit();
        final int limit = event.getPage() * event.getLimit();

        final Set<Chef> chefs = this.chefMapper.findMany(new RowBounds(offset, limit));
        final long total = this.chefMapper.count();

        return new AsyncResult<>(CatalogChefEvent.builder().chefs(chefs).total(total).build());
    }

    @Override
    @Async
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> createChef(final CreateChefEvent event) {

        event.getChef().setStatus(ChefStatus.PRE_REGISTERED);
        event.getChef().setRating(0F);

        this.accountMapper.create(event);
        this.chefMapper.create(event);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @Cacheable(value = "chefs")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<ResponseChefEvent> requestChef(final RequestChefEvent event) {

        final Chef chef = this.chefMapper.findOne(event);

        return new AsyncResult<>(ResponseChefEvent.builder().chef(chef).build());
    }

    @Override
    @Async
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> updateChef(final UpdateChefEvent event) {

        return Optional.of(this.chefMapper.findAccountUUID(event.getChef().getId()))
                .map(id -> {
                    event.getChef().getAccount().setId(id);

                    this.accountMapper.update(event);
                    this.chefMapper.update(event);

                    Stream.concat(this.telephoneMapper.findManyByChef(event.getChef().getId())
                            .stream(), event.getChef().getTelephones()
                            .stream())
                            .filter(new ConcurrentSkipListSet<>(Comparator.comparing(Telephone::getNumber))::add)
                            .parallel()
                            .forEach(t -> this.telephoneMapper.create(t, event.getChef().getId()));

                    return new AsyncResult<>(ResponseChefEvent.builder().chef(null).build());
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    @Async
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> deleteChef(final DeleteChefEvent event) {

        return Optional.of(this.chefMapper.findAccountUUID(event.getId()))
                .map(id -> {
                    this.chefMapper.delete(event);
                    this.accountMapper.delete(id);

                    return new AsyncResult<>(ResponseChefEvent.builder().chef(null).build());
                })
                .orElseThrow(RuntimeException::new);
    }
}
