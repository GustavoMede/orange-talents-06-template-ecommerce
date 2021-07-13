package br.com.zupacademy.gustavo.mercadolivre.security;

import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenManager {

    @Value("${mercadolivre.jwt.secret}")
    private String secret;

    //Gera o Token.
    public String gerarToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("API Mercado Livre")
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(
                        Date.from(LocalDateTime.now()
                        .plusHours(1)
                        .atZone(ZoneId.of("America/Sao_Paulo")).toInstant())
                ).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    // Valida a assinatura do Token. Verifica se o Token é valido ou não.
    public boolean validaToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Pega o login do usuário.
    public String getUserName(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
