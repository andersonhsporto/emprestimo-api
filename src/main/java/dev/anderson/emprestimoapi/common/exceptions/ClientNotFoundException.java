package dev.anderson.emprestimoapi.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(String cpf) {
        super("Cliente com o CPF: " + cpf + " não foi encontrado");
    }
}
