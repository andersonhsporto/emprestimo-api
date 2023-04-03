package dev.anderson.emprestimoapi.service;

import dev.anderson.emprestimoapi.dto.ClientDto;
import dev.anderson.emprestimoapi.entities.ClientEntity;
import dev.anderson.emprestimoapi.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.mapper.ClientMapper;
import dev.anderson.emprestimoapi.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClientDto getClientByCpf(String cpf) throws ClientNotFoundException {
        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);

            return clientMapper.toDto(clientEntity);
        }
        throw new ClientNotFoundException("Cliente não encontrado");
    }

    public void deleteClientByCpf(String cpf) throws ClientNotFoundException {
        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);

            clientRepository.delete(clientEntity);
        }
        throw new ClientNotFoundException("Cliente não encontrado");
    }

    public String updateClientByCpf(String cpf, ClientDto clientDto) throws Exception {
        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);

            clientMapper.updateClientEntity(clientDto, clientEntity);
            clientRepository.save(clientEntity);
            return clientDto.toString();
        }
        throw new ClientNotFoundException("Cliente não encontrado");
    }


}
