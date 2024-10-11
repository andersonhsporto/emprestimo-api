package dev.anderson.emprestimoapi.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClientDuplicatedException extends Exception {
    public ClientDuplicatedException(String cpf) {
        super("Cliente com o CPF " + cpf + " já cadastrado");
    }
}