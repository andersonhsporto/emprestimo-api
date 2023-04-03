package dev.anderson.emprestimoapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoanNotFoundException extends Exception {
    public LoanNotFoundException(String cpf, Long id) {
        super("Empréstimo com o ID: " + id + " não foi encontrado para o cliente com o CPF: " + cpf);
    }
}
