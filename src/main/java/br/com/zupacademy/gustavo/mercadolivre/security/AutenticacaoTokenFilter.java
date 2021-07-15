package br.com.zupacademy.gustavo.mercadolivre.security;

import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;

    private AutenticacaoManager autenticacaoManager;

    public AutenticacaoTokenFilter(TokenManager tokenManager, AutenticacaoManager autenticacaoManager) {
        this.tokenManager = tokenManager;
        this.autenticacaoManager = autenticacaoManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperaToken(httpServletRequest);
        if(token == null || token.isEmpty()){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            boolean valido = tokenManager.validaToken(token);
            if (valido) {
                autenticaCliente(token);
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
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
        if(Objects.nonNull(token) && !token.isEmpty()){
                return token;
        }
        return null;
    }
}
