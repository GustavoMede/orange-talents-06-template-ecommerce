package br.com.zupacademy.gustavo.mercadolivre.utils;

import br.com.zupacademy.gustavo.mercadolivre.dto.PerguntaDto;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmail {

    public void enviaEmailPergunta(PerguntaDto perguntaDto){
        System.out.println("Seu produto possui uma nova pergunta\n"
        + perguntaDto.getTitulo());
    }

    public void enviaEmailCompra(String email){
        System.out.println(email);
    }
}
