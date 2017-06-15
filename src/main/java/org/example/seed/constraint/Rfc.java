package org.example.seed.constraint;

import org.example.seed.constraint.impl.RfcImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Created by PINA on 08/06/2017.
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RfcImpl.class)
@Documented
@Size(min = 13, max = 13)
public @interface Rfc {

    String message() default "RFC";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
