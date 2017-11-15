package org.example.seed.constraint.impl;

import org.example.seed.constraint.Text;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by PINA on 30/06/2017.
 */
public class TextImpl implements ConstraintValidator<Text, String> {

  private Text text;

  @Override
  public void initialize(final Text text) {
    this.text = text;
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
    return value == null || value.matches("^[a-zA-Z0-9?$()'!,+\\-:.€£%\\s]+$");
  }
}
