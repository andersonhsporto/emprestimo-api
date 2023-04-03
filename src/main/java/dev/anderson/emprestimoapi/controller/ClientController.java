package dev.anderson.emprestimoapi.controller;

import dev.anderson.emprestimoapi.dto.ClientDto;
import dev.anderson.emprestimoapi.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/clientes")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String makeClient(@Valid @RequestBody ClientDto clientDto) {
        return clientService.makeClient(clientDto);
    }
}
