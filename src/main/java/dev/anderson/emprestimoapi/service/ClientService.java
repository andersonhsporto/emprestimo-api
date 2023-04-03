package dev.anderson.emprestimoapi.service;

import dev.anderson.emprestimoapi.dto.ClientDto;
import dev.anderson.emprestimoapi.entities.ClientEntity;
import dev.anderson.emprestimoapi.mapper.ClientMapper;
import dev.anderson.emprestimoapi.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    private ClientMapper clientMapper;

    public String makeClient(ClientDto clientDto) {
        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientRepository.save(clientEntity);
        return clientDto.toString();
    }


}
