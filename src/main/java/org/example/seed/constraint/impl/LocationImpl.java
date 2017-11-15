package org.example.seed.constraint.impl;

import org.example.seed.constraint.Location;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by PINA on 30/06/2017.
 */
public class LocationImpl implements ConstraintValidator<Location, String> {

  private Location location;

  @Override
  public void initialize(final Location location) {
    this.location = location;
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
    return value == null || value.matches("^([^*|&~^@#$%<>=()\\[\\]{}_+?¿!/¡/\\\\/])+$");
  }
}
