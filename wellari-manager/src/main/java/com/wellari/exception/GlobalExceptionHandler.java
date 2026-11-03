package com.wellari.exception;
import com.wellari.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarProdutoNaoEncontrado(ProdutoNaoEncontradoException exception){

                ErroResposta erro = new ErroResposta (
                HttpStatus.NOT_FOUND.value(),
                List.of(exception.getMessage()),
                LocalDateTime.now()
        );
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(erro);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarErrodeValidacao(MethodArgumentNotValidException exception){

        List<String> messagens = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .toList();



        ErroResposta resposta = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                messagens,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }
}
