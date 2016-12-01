package org.example.seed.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by Ricardo Pina Arellano on 24/11/2016.
 */
@Data
public abstract class Momentum {

    protected Date registerDate;
    protected Date changeDate;
}
