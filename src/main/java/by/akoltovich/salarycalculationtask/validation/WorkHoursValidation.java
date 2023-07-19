package by.akoltovich.salarycalculationtask.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BeginAndEndValidation.class)
@Documented
public @interface WorkHoursValidation {

    String message() default "There must be more than one working hours, the end cannot be before the start";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
