//package br.com.zupacademy.gustavo.mercadolivre.handler;
//
//import br.com.zupacademy.gustavo.mercadolivre.exception.ProdutoDuplicadoException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestControllerAdvice
//public class ExceptionHandlers extends ResponseEntityExceptionHandler {
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        List<FieldError> descricaoErroCampos = ex.getBindingResult().getFieldErrors();
//        List<ObjectError> descricaoErroGlobais = ex.getBindingResult().getGlobalErrors();
//
//        ErroValidacaoDTO erro = new ErroValidacaoDTO(LocalDateTime.now(), descricaoErroCampos, descricaoErroGlobais);
//        return new ResponseEntity<>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ProdutoDuplicadoException.class)
//    public final ResponseEntity<Object> handleAllExceptions(ProdutoDuplicadoException ex) {
//        ProdutoDuplicadoException erro =
//                new ProdutoDuplicadoException(ex.getMessage());
//        return new ResponseEntity(erro.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//}

