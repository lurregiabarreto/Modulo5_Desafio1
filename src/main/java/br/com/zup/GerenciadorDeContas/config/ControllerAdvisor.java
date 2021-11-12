package br.com.zup.GerenciadorDeContas.config;

import br.com.zup.GerenciadorDeContas.exception.ContaNaoEncontradaException;
import br.com.zup.GerenciadorDeContas.exception.StatusIncorretoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<MensagemDeErro> manipularErrosValidacao(MethodArgumentNotValidException exception) {
        List<MensagemDeErro> erros = new ArrayList<>();

        for (FieldError referencia : exception.getFieldErrors()) {
            MensagemDeErro mensagemDeErro = new MensagemDeErro(referencia.getDefaultMessage());
            erros.add(mensagemDeErro);
        }

        return erros;

    }
    @ExceptionHandler(ContaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemDeErro ExcecaoDeContaNaoLocalizada(ContaNaoEncontradaException exception) {
        return new MensagemDeErro(exception.getMessage());
    }
    @ExceptionHandler(StatusIncorretoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemDeErro statusInvalidoException(StatusIncorretoException exception) {
        return new MensagemDeErro(exception.getLocalizedMessage());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemDeErro ExcecaoDeEnumInvalido(HttpMessageNotReadableException exception) {
        return new MensagemDeErro("Tipo inválido!");
    }


}

