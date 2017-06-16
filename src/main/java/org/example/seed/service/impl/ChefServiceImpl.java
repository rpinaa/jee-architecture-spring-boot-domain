package org.example.seed.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.example.seed.catalog.ChefStatus;
import org.example.seed.domain.Chef;
import org.example.seed.event.chef.*;
import org.example.seed.mapper.AccountMapper;
import org.example.seed.mapper.ChefMapper;
import org.example.seed.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * Created by PINA on 15/06/2017.
 */
@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ChefMapper chefMapper;

    @Override
    @Async
    @Cacheable(value = "chefs")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<CatalogChefEvent> requestAllChefs(final RequestAllChefEvent chefEvent) {

        final int offset = (chefEvent.getPage() - 1) * chefEvent.getLimit();
        final int limit = chefEvent.getPage() * chefEvent.getLimit();

        final Set<Chef> chefs = this.chefMapper.findChefs(new RowBounds(offset, limit));
        final long total = this.chefMapper.countChefs();

        return new AsyncResult<>(CatalogChefEvent.builder().chefs(chefs).total(total).build());
    }

    @Override
    @Async
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> createChef(final CreateChefEvent chefEvent) {

        chefEvent.getChef().setStatus(ChefStatus.PRE_REGISTERED);
        chefEvent.getChef().setRating(0F);

        this.accountMapper.createAccount(chefEvent);
        this.chefMapper.createChef(chefEvent);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @Cacheable(value = "chefs")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<ResponseChefEvent> requestChef(final RequestChefEvent chefEvent) {

        final Chef chef = this.chefMapper.findChef(chefEvent);

        return new AsyncResult<>(ResponseChefEvent.builder().chef(chef).build());
    }

    @Override
    @Async
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> updateChef(final UpdateChefEvent chefEvent) {

        final RequestChefEvent requestChefEvent = RequestChefEvent.builder()
                .id(chefEvent.getChef().getId()).build();

        final Optional<UUID> uuid = Optional.of(this.accountMapper.findAccount(requestChefEvent));

        uuid.ifPresent(id -> {
            chefEvent.getChef().getAccount().setId(id);

            this.accountMapper.updateAccount(chefEvent);
            this.chefMapper.updateChef(chefEvent);
        });

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> deleteChef(final DeleteChefEvent chefEvent) {

        final RequestChefEvent requestChefEvent = RequestChefEvent.builder()
                .id(chefEvent.getId()).build();

        final Optional<UUID> uuid = Optional.of(this.accountMapper.findAccount(requestChefEvent));

        uuid.ifPresent(id -> {
            this.chefMapper.deleteChef(chefEvent);
            this.accountMapper.deleteAccount(id);
        });

        return new AsyncResult<>(null);
    }
}
