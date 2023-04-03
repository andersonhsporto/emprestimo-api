package dev.anderson.emprestimoapi.controller;

import dev.anderson.emprestimoapi.dto.ClientDto;
import dev.anderson.emprestimoapi.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClientByCpf(@PathVariable String cpf) throws Exception {
        return clientService.getClientByCpf(cpf);
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientByCpf(@PathVariable String cpf) throws Exception {
        clientService.deleteClientByCpf(cpf);
    }

    @PutMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public String updateClientByCpf(
            @PathVariable String cpf,
            @Valid @RequestBody ClientDto clientDto) throws Exception {
        return clientService.updateClientByCpf(cpf, clientDto);
    }
}
