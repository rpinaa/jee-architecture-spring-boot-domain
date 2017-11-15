package org.example.seed.constraint;

import org.example.seed.constraint.impl.LocationImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by PINA on 30/06/2017.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocationImpl.class)
@Documented
public @interface Location {

  String message() default "must match Location pattern";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
