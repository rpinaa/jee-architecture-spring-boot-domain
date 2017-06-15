package org.example.seed.event.chef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.seed.domain.Chef;

import java.util.Set;

/**
 * Created by PINA on 31/05/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogChefEvent {

    private long total;
    private Set<Chef> chefs;
}
