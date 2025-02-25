package dev.anderson.emprestimoapi.client;

import dev.anderson.emprestimoapi.loan.LoanEntity;
import dev.anderson.emprestimoapi.common.exceptions.ClientDuplicatedException;
import dev.anderson.emprestimoapi.common.exceptions.ClientNotFoundException;
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
        if (updateCpfExists(cpf, clientDto)) {
            throw new ClientDuplicatedException(clientDto.getCpf());
        } else if (!clientRepository.existsByCpf(cpf)) {
            throw new ClientNotFoundException(cpf);
        } else {
            return updatePersist(cpf, clientDto);
        }
    }

    private boolean updateCpfExists(String cpf, ClientDto clientDto) {
        return clientRepository.existsByCpf(clientDto.getCpf()) && !clientDto.getCpf().equals(cpf);
    }

    private ClientDto updatePersist(String cpf, ClientDto clientDto) {
        ClientEntity clientEntity = clientRepository.findByCpf(cpf);

        clientMapper.updateClientEntity(clientDto, clientEntity);
        if (!cpf.equals(clientDto.getCpf())) {
            updateAllCpf(clientDto.getCpf(), clientEntity);
        }
        clientRepository.save(clientEntity);
        return clientMapper.toDto(clientEntity);
    }

    private void updateAllCpf(String cpf, ClientEntity clientEntity) {
        List<LoanEntity> loanEntityList = clientEntity.getLoans();

        for (LoanEntity loanEntity : loanEntityList) {
            loanEntity.setCpfClient(cpf);
        }
    }

}
