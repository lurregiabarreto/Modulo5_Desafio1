package br.com.zup.GerenciadorDeContas.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<MensagemDeErro> manipularErrosValidacao(MethodArgumentNotValidException exception) {
        List<MensagemDeErro> erros = new ArrayList<>();

        for (FieldError referencia : exception.getFieldErrors()) {
            MensagemDeErro erroValidacao = new MensagemDeErro(referencia.getField(),
                    referencia.getDefaultMessage());
            erros.add(erroValidacao);
        }

        return erros;

    }
}

