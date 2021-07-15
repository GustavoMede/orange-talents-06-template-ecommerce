package br.com.zupacademy.gustavo.mercadolivre.annotation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CampoDuplicadoValidator implements ConstraintValidator<CampoDuplicado, Object> {

    @PersistenceContext
    private EntityManager entityManager;
    private String nomeCampo;
    private Class<?> nomeClasse;


    @Override
    public void initialize(CampoDuplicado constraintAnnotation) {
        nomeCampo = constraintAnnotation.fieldName();
        nomeClasse = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + nomeClasse.getName()+" where "+nomeCampo+"=:value");
        query.setParameter("value", o);
        List<?> resultadoConsulta = query.getResultList();
        Assert.state(resultadoConsulta.size() <= 1, "O "+nomeCampo+" digitado já foi cadastrado no sistema.");

        return resultadoConsulta.isEmpty();
    }
}
