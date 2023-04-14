package dev.anderson.emprestimoapi.service;

import dev.anderson.emprestimoapi.dto.ClientDto;
import dev.anderson.emprestimoapi.entities.ClientEntity;
import dev.anderson.emprestimoapi.exceptions.ClientDuplicatedException;
import dev.anderson.emprestimoapi.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.mapper.ClientMapper;
import dev.anderson.emprestimoapi.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    private ClientMapper clientMapper;

    public ClientDto makeClient(ClientDto clientDto) throws ClientDuplicatedException {
        if (!clientRepository.existsByCpf(clientDto.getCpf())) {
            ClientEntity clientEntity = clientMapper.toModel(clientDto);

            clientRepository.save(clientEntity);
            return clientMapper.toDto(clientEntity);
        }
        throw new ClientDuplicatedException(clientDto.getCpf());
    }

    public List<ClientDto> getAllClients() {
        List<ClientEntity> clientEntityList = clientRepository.findAll();

        return clientMapper.toDtoList(clientEntityList);
    }

    public ClientDto getClientByCpf(String cpf) throws ClientNotFoundException {
        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);

            return clientMapper.toDto(clientEntity);
        } else {
            throw new ClientNotFoundException(cpf);
        }
    }

    public void deleteClientByCpf(String cpf) throws ClientNotFoundException {
        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);

            clientRepository.delete(clientEntity);
        } else {
            throw new ClientNotFoundException(cpf);
        }
    }

    public ClientDto updateClientByCpf(String cpf, ClientDto clientDto) throws ClientNotFoundException, ClientDuplicatedException {
        if (!clientDto.getCpf().isEmpty() && clientRepository.existsByCpf(clientDto.getCpf())) {
            throw new ClientDuplicatedException(clientDto.getCpf());
        }

        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);

            clientMapper.updateClientEntity(clientDto, clientEntity);
            clientRepository.save(clientEntity);
            return clientMapper.toDto(clientEntity);
        } else {
            throw new ClientNotFoundException(cpf);
        }
    }

}
