package org.example.seed.service;

import org.example.seed.event.chef.*;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by PINA on 15/06/2017.
 */
public interface ChefService {

  ListenableFuture<CatalogChefEvent> requestAllChefs(final RequestAllChefEvent event);

  ListenableFuture<ResponseChefEvent> createChef(final CreateChefEvent event);

  ListenableFuture<ResponseChefEvent> requestChef(final RequestChefEvent event);

  ListenableFuture<ResponseChefEvent> updateChef(final UpdateChefEvent event);

  ListenableFuture<ResponseChefEvent> deleteChef(final DeleteChefEvent event);
}
