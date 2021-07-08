package br.com.zupacademy.gustavo.mercadolivre.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CampoDuplicadoValidator.class})
public @interface CampoDuplicado {

    String message() default "O valor inserido já existe.";

    String fieldName();

    Class<?> domainClass();

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};
}
