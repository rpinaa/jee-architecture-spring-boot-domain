package org.example.seed.service;

import org.example.seed.event.chef.*;

import java.util.concurrent.Future;

/**
 * Created by PINA on 15/06/2017.
 */
public interface ChefService {

    Future<CatalogChefEvent> requestAllChefs(final RequestAllChefEvent requestAllChefEvent);

    Future<ResponseChefEvent> createChef(final CreateChefEvent createChefEvent);

    Future<ResponseChefEvent> requestChef(final RequestChefEvent requestChefEvent);

    Future<ResponseChefEvent> updateChef(final UpdateChefEvent updateChefEvent);

    void deleteChef(final DeleteChefEvent deleteChefEvent);
}
