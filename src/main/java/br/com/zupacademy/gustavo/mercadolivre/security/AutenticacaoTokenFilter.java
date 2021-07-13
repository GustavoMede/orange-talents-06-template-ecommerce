package br.com.zupacademy.gustavo.mercadolivre.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityManager;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;

    private AutenticacaoManager autenticacaoManager;

    private EntityManager entityManager;

    public AutenticacaoTokenFilter(TokenManager tokenManager, EntityManager entityManager, AutenticacaoManager autenticacaoManager) {
        this.tokenManager = tokenManager;
        this.entityManager = entityManager;
        this.autenticacaoManager = autenticacaoManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperaToken(httpServletRequest);
        boolean valido = tokenManager.validaToken(token);
        if(valido){
            autenticaCliente(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticaCliente(String token) {
        String username = tokenManager.getUserName(token);
        UserDetails usuario = autenticacaoManager.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

        //Garante que o usuário está autenticado.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperaToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null || token.isEmpty()){
            return null;
        }
        return token.substring(7, token.length());
    }
}
