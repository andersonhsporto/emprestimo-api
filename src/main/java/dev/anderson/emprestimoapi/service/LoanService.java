package dev.anderson.emprestimoapi.service;

import dev.anderson.emprestimoapi.dto.LoanDto;
import dev.anderson.emprestimoapi.entities.ClientEntity;
import dev.anderson.emprestimoapi.entities.LoanEntity;
import dev.anderson.emprestimoapi.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.mapper.LoanMapper;
import dev.anderson.emprestimoapi.repositories.ClientRepository;
import dev.anderson.emprestimoapi.repositories.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanService {

    private ClientRepository clientRepository;

    private LoanRepository loanRepository;

    private LoanMapper loanMapper;

    public String makeLoan(String cpf, LoanDto loanDto) throws ClientNotFoundException {
        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);
            LoanEntity loanEntity = loanMapper.toModel(loanDto);

            // TODO: implementar regra de negócio para cálculo do valor final do empréstimo

            clientEntity.addLoan(loanEntity);
            clientRepository.save(clientEntity);
            return loanDto.toString();
        }
        throw new ClientNotFoundException("Cliente não encontrado");

    }


}
