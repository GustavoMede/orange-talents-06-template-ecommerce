package br.com.zupacademy.gustavo.mercadolivre.annotation;

import br.com.zupacademy.gustavo.mercadolivre.dto.ListasCaracteristicasRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class ValidaListaValidator implements ConstraintValidator<ValidaLista, List<ListasCaracteristicasRequest>> {


    @Override
    public void initialize(ValidaLista constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<ListasCaracteristicasRequest> listasCaracteristicasRequests, ConstraintValidatorContext constraintValidatorContext) {
        return listasCaracteristicasRequests.size() >= 3;
    }
}
