package br.com.zupacademy.gustavo.mercadolivre.utils;

import br.com.zupacademy.gustavo.mercadolivre.dto.PerguntaRequestDto;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmail {

    public void envia(PerguntaRequestDto perguntaRequestDto){
        System.out.println("Seu produto possui uma nova pergunta\n"
        + perguntaRequestDto.getTitulo());
    }
}
