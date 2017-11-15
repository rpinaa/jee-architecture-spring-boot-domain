package org.example.seed.rest;

import org.example.seed.event.chef.*;
import org.example.seed.group.chef.ChefCreateGroup;
import org.example.seed.group.chef.ChefUpdateGroup;
import org.example.seed.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by PINA on 15/06/2017.
 */
@RestController
@RequestMapping(path = ChefRest.CHEF_ROOT_PATH)
public class ChefRest {

  public static final String CHEF_ROOT_PATH =  "/chefs";
  public static final String CHEF_CRUD_PATH =  "/{chefId}";
  public static final String ORDER_CRUD_PATH =  "/{chefId}/orders";

  private final ChefService chefService;

  @Autowired
  public ChefRest(final ChefService chefService) {
    this.chefService = chefService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Callable<CatalogChefEvent> getAllChefs(
    @RequestParam("page") final int page, @RequestParam("limit") final int limit
  )
    throws ExecutionException, InterruptedException {

    final RequestAllChefEvent requestAllChefEvent = RequestAllChefEvent.builder()
      .page(page)
      .limit(limit)
      .build();

    final CatalogChefEvent catalogChefEvent = this.chefService.requestAllChefs(requestAllChefEvent).get();

    return () -> catalogChefEvent;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Callable<ResponseChefEvent> createChef(
    @RequestBody @Validated(value = {ChefCreateGroup.class}) final CreateChefEvent event
  )
    throws ExecutionException, InterruptedException {

    final ResponseChefEvent responseChefEvent = this.chefService.createChef(event).get();

    return () -> responseChefEvent;
  }

  @GetMapping(ChefRest.CHEF_CRUD_PATH)
  @ResponseStatus(HttpStatus.OK)
  public Callable<ResponseChefEvent> getChef(@PathVariable("chefId") final UUID idChef)
    throws ExecutionException, InterruptedException {

    final RequestChefEvent requestChefEvent = RequestChefEvent.builder().id(idChef).build();

    final ResponseChefEvent responseChefEvent = this.chefService.requestChef(requestChefEvent).get();

    return () -> responseChefEvent;
  }

  @PutMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Callable<ResponseChefEvent> updateChef(
    @RequestBody @Validated(value = {ChefUpdateGroup.class}) final UpdateChefEvent event
  )
    throws ExecutionException, InterruptedException {

    final ResponseChefEvent responseChefEvent = this.chefService.updateChef(event).get();

    return () -> responseChefEvent;
  }

  @DeleteMapping(ChefRest.CHEF_CRUD_PATH)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Callable<ResponseChefEvent> deleteChef(@PathVariable("chefId") final UUID idChef)
    throws ExecutionException, InterruptedException {

    final DeleteChefEvent deleteChefEvent = DeleteChefEvent.builder().id(idChef).build();

    final ResponseChefEvent responseChefEvent = this.chefService.deleteChef(deleteChefEvent).get();

    return () -> responseChefEvent;
  }
}
