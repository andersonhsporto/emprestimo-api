package dev.anderson.emprestimoapi.exceptions.handler;

import dev.anderson.emprestimoapi.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.exceptions.LoanNotFoundException;
import dev.anderson.emprestimoapi.exceptions.MaxLoanException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ClientNotFoundException.class, LoanNotFoundException.class})
    public ResponseEntity<Map<String, List<String>>> handleNotFound(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());

        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxLoanException.class)
    public ResponseEntity<Map<String, List<String>>> handleMaxLoanException(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());

        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        errorsMap.put("errors", errors);
        return errorsMap;
    }
}
