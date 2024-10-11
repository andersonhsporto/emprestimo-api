package dev.anderson.emprestimoapi.client;

import dev.anderson.emprestimoapi.common.exceptions.ClientDuplicatedException;
import dev.anderson.emprestimoapi.common.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.common.handler.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClientController extends GlobalExceptionHandler {

    private ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto makeClient(@Valid @RequestBody ClientDto clientDto) throws ClientDuplicatedException {
        return clientService.makeClient(clientDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClientByCpf(@PathVariable String cpf) throws ClientNotFoundException {
        return clientService.getClientByCpf(cpf);
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientByCpf(@PathVariable String cpf) throws ClientNotFoundException {
        clientService.deleteClientByCpf(cpf);
    }

    @PutMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto updateClientByCpf(
            @PathVariable String cpf,
            @Valid @RequestBody ClientDto clientDto) throws ClientNotFoundException, ClientDuplicatedException {
        return clientService.updateClientByCpf(cpf, clientDto);
    }
}
