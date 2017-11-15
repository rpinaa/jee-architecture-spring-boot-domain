package org.example.seed.constraint.impl;

import org.example.seed.constraint.Curp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by PINA on 08/06/2017.
 */
public class CurpImpl implements ConstraintValidator<Curp, String> {

  private Curp curp;

  @Override
  public void initialize(final Curp curp) {
    this.curp = curp;
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
    return value == null || value.matches("^[A-Z]{4}[\\d]{6}(H|M){1}(AS|BC|BS|CC|CH|CL|CM|CS|DF|DG|GR|GT|HG|JC|MC|MN|MS|NE|NL|NT|OC|PL|QR|QT|SL|SP|SR|TC|TL|TS|VZ|YN|ZS){1}[A-Z]{3}([\\d]|[A-Z]){1}[\\d]{1}$");
  }
}
