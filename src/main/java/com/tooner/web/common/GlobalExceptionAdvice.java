package com.tooner.web.common;


import com.tooner.web.dto.ExceptionResponseDto;
import com.tooner.web.exception.DuplicatedUserNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    // TODO: exception을 어떻게 응답 처리할 것인지 방법을 고민해보자

    @ExceptionHandler({
            DuplicatedUserNameException.class, UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ExceptionResponseDto> validated(Exception exception) {
        return ResponseEntity.
                badRequest().
                body(new ExceptionResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> exceptionHandler(Exception exception) {
        log.error("에러 발생!", exception);

        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new ExceptionResponseDto(exception.getMessage()));
    }
}
