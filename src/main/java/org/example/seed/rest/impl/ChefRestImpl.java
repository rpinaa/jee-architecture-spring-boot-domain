package org.example.seed.rest.impl;

import org.example.seed.event.chef.*;
import org.example.seed.group.chef.ChefCreateGroup;
import org.example.seed.group.chef.ChefUpdateGroup;
import org.example.seed.rest.ChefRest;
import org.example.seed.service.ChefService;
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
 * Created by PINA on 15/06/2017.
 */
@RestController
public class ChefRestImpl implements ChefRest {

    @Autowired
    private ChefService chefService;

    @Override
    public Callable<CatalogChefEvent> getAllChefs(@RequestParam("page") final int page, @RequestParam("limit") final int limit)
            throws ExecutionException, InterruptedException {

        final RequestAllChefEvent requestAllChefEvent = RequestAllChefEvent.builder()
                .page(page)
                .limit(limit)
                .build();

        final CatalogChefEvent catalogChefEvent = this.chefService.requestAllChefs(requestAllChefEvent).get();

        return () -> catalogChefEvent;
    }

    @Override
    public Callable<ResponseChefEvent> createChef(@RequestBody @Validated(value = {ChefCreateGroup.class}) final CreateChefEvent chefEvent)
            throws ExecutionException, InterruptedException {

        final ResponseChefEvent responseChefEvent = this.chefService.createChef(chefEvent).get();

        return () -> responseChefEvent;
    }

    @Override
    public Callable<ResponseChefEvent> getChef(@PathVariable("id") final UUID id)
            throws ExecutionException, InterruptedException {

        final RequestChefEvent requestChefEvent = RequestChefEvent.builder().id(id).build();

        final ResponseChefEvent responseChefEvent = this.chefService.requestChef(requestChefEvent).get();

        return () -> responseChefEvent;
    }

    @Override
    public Callable<ResponseChefEvent> updateChef(@RequestBody @Validated(value = {ChefUpdateGroup.class}) final UpdateChefEvent chefEvent)
            throws ExecutionException, InterruptedException {

        final ResponseChefEvent responseChefEvent = this.chefService.updateChef(chefEvent).get();

        return () -> responseChefEvent;
    }

    @Override
    public Callable<ResponseChefEvent> deleteChef(@PathVariable("id") final UUID id)
            throws ExecutionException, InterruptedException {

        final DeleteChefEvent deleteChefEvent = DeleteChefEvent.builder().id(id).build();

        final ResponseChefEvent responseChefEvent = this.chefService.deleteChef(deleteChefEvent).get();

        return () -> responseChefEvent;
    }
}
