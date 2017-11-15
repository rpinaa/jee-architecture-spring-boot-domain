package org.example.seed.constraint;

import org.example.seed.constraint.impl.TextImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by PINA on 30/06/2017.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TextImpl.class)
@Documented
public @interface Text {

  String message() default "must match text pattern";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
