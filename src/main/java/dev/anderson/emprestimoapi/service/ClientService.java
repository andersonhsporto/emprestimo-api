package dev.anderson.emprestimoapi.service;

import dev.anderson.emprestimoapi.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;


}
