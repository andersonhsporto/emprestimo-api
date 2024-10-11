package dev.anderson.emprestimoapi.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxLoanException extends Exception {
    public MaxLoanException(String message) {
        super("Este Empréstimo ultrapassa o limite do cliente: " + message);
    }
}
