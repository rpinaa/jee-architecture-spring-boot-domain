package org.example.seed.constraint;

import org.example.seed.constraint.impl.OneImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneImpl.class)
@Documented
public @interface One {

  String message() default "must have 0 items";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
