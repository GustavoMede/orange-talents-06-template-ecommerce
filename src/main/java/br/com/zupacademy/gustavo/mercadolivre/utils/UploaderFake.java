package br.com.zupacademy.gustavo.mercadolivre.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploaderFake {

    public Set<String>  envia(List<MultipartFile> fotos){
        return fotos.stream()
                .map(foto -> "http://bucket.io/"
                        + foto.getOriginalFilename()).collect(Collectors.toSet());
    }
}
