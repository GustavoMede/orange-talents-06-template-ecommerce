package br.com.zupacademy.gustavo.mercadolivre.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidaListaValidator.class})
public @interface ValidaLista {

    String message() default "O produto precisa ter pelo menos três características.";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};
}
