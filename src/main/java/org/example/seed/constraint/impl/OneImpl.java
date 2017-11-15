package org.example.seed.constraint.impl;

import org.example.seed.constraint.One;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class OneImpl implements ConstraintValidator<One, List> {

  private One one;

  @Override
  public void initialize(final One one) {
    this.one = one;
  }

  @Override
  public boolean isValid(final List value, final ConstraintValidatorContext constraintValidatorContext) {
    return value == null || value.size() == 0;
  }
}
