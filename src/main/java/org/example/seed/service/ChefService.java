package org.example.seed.service;

import org.example.seed.event.chef.*;

import java.util.concurrent.Future;

/**
 * Created by PINA on 15/06/2017.
 */
public interface ChefService {

    Future<CatalogChefEvent> requestAllChefs(final RequestAllChefEvent chefEvent);

    Future<ResponseChefEvent> createChef(final CreateChefEvent chefEvent);

    Future<ResponseChefEvent> requestChef(final RequestChefEvent chefEvent);

    Future<ResponseChefEvent> updateChef(final UpdateChefEvent chefEvent);

    Future<ResponseChefEvent> deleteChef(final DeleteChefEvent chefEvent);
}
