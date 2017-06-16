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
    public Future<CatalogChefEvent> requestAllChefs(final RequestAllChefEvent requestAllChefEvent) {

        final int offset = (requestAllChefEvent.getPage() - 1) * requestAllChefEvent.getLimit();
        final int limit = requestAllChefEvent.getPage() * requestAllChefEvent.getLimit();

        final Set<Chef> chefs = this.chefMapper.findAllChefs(new RowBounds(offset, limit));
        final long total = this.chefMapper.countAllChefs();

        return new AsyncResult<>(CatalogChefEvent.builder().chefs(chefs).total(total).build());
    }

    @Override
    @Async
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> createChef(final CreateChefEvent createChefEvent) {

        createChefEvent.getChef().setStatus(ChefStatus.PRE_REGISTERED);
        createChefEvent.getChef().setRating(0F);

        this.accountMapper.createAccount(createChefEvent);
        this.chefMapper.createChef(createChefEvent);

        return new AsyncResult<>(null);
    }

    @Override
    @Async
    @Cacheable(value = "chefs")
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Future<ResponseChefEvent> requestChef(final RequestChefEvent requestChefEvent) {

        final Chef chef = this.chefMapper.findChefById(requestChefEvent);

        return new AsyncResult<>(ResponseChefEvent.builder().chef(chef).build());
    }

    @Override
    @Async
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> updateChef(final UpdateChefEvent updateChefEvent) {

        this.chefMapper.saveChef(updateChefEvent);

        return new AsyncResult<>(null);
    }

    @Override
    @CacheEvict(value = "chefs", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Future<ResponseChefEvent> deleteChef(final DeleteChefEvent deleteChefEvent) {

        final Optional<UUID> uuidAccount = Optional.of(this.accountMapper.findAccountId(deleteChefEvent));

        uuidAccount.ifPresent(uuid -> {
            this.chefMapper.deleteChef(deleteChefEvent);
            this.accountMapper.deleteAccount(uuid);
        });

        return new AsyncResult<>(null);
    }
}
