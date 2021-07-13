package br.com.zupacademy.gustavo.mercadolivre.security;

import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AutenticacaoManager implements UserDetailsService {

    @PersistenceContext
    EntityManager entityManager;
    @Value("${security.username-query}")
    private String query;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query consulta = entityManager.createQuery(query)
                .setParameter("username", username);
        List<?> resultadoConsulta = consulta.getResultList();
        Assert.isTrue(resultadoConsulta.size() <= 1, "Mais de um usuário cadastrado com o mesmo username.");
        if (resultadoConsulta.isEmpty()) {
            throw new UsernameNotFoundException("Não foi possível encontrar um usuário com email:" + username);
        }
        Object objeto = consulta.getSingleResult();
        var usuario = (Usuario) objeto;

        return usuario;
    }
}
