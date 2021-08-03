package br.com.zupacademy.gustavo.mercadolivre.handler;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;

public class ErroValidacaoDTO {
    private LocalDateTime currentDate;
    private List<FieldError> message;
    private List<ObjectError> messageList;

    public ErroValidacaoDTO(){
    }

    public ErroValidacaoDTO(LocalDateTime currentDate, List<FieldError> message, List<ObjectError> messageList) {
        this.currentDate = currentDate;
        this.message = message;
        this.messageList = messageList;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public List<ObjectError> getMessageList() {
        return messageList;
    }

    public List<FieldError> getMessage() {
        return message;
    }
}
