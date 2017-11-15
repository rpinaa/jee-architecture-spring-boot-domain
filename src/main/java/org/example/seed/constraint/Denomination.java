package org.example.seed.constraint;

import org.example.seed.constraint.impl.DenominationImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by PINA on 30/06/2017.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DenominationImpl.class)
@Documented
public @interface Denomination {

  String message() default "must match latin pattern";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
