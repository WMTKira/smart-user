package com.wmt.smartuser.exception;

import com.wmt.smartuser.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wmtumanday
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        log.info("MethodArgumentNotValidException: {}",errorMap);
        return ResponseVo.fail(errorMap);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleValidationException(IllegalArgumentException exception) {
        log.info("IllegalArgumentException: {}",exception.getMessage());
        return ResponseVo.fail(exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleValidationException(UsernameNotFoundException exception) {
        log.info("UsernameNotFoundException: {}",exception.getMessage());
        return ResponseVo.fail(exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleValidationException(BadCredentialsException exception) {
        log.info("BadCredentialsException: {}",exception.getMessage());
        return ResponseVo.fail(exception.getMessage());
    }
}
