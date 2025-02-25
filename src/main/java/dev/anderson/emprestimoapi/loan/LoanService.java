package dev.anderson.emprestimoapi.loan;

import dev.anderson.emprestimoapi.client.ClientEntity;
import dev.anderson.emprestimoapi.common.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.common.exceptions.LoanNotFoundException;
import dev.anderson.emprestimoapi.common.exceptions.MaxLoanException;
import dev.anderson.emprestimoapi.client.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanService {

    private ClientRepository clientRepository;

    private LoanRepository loanRepository;

    private LoanMapper loanMapper;

    public LoanDto makeLoan(String cpf, LoanDto loanDto) throws ClientNotFoundException, MaxLoanException {
        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);
            BigDecimal startValue = loanDto.getStartValue();

            if (clientEntity.isEligibleForLoan(startValue)) {
                return persistLoan(clientEntity, loanDto);
            }
            throw new MaxLoanException(clientEntity.getCpf());
        }
        throw new ClientNotFoundException(cpf);
    }

    public void deleteLoan(String cpf, Long id) throws ClientNotFoundException, LoanNotFoundException {
        if (clientRepository.existsByCpf(cpf)) {
            if (loanRepository.existsByIdAndAndCpfClient(id, cpf)) {
                ClientEntity clientEntity = clientRepository.findByCpf(cpf);
                Optional<LoanEntity> loanEntity = loanRepository.findById(id);

                if (loanEntity.isPresent()) {
                    clientEntity.removeLoan(loanEntity.get());
                    loanRepository.delete(loanEntity.get());
                    return;
                }
            }
            throw new LoanNotFoundException(cpf, id);
        }
        throw new ClientNotFoundException(cpf);
    }

    public LoanDto getLoan(String cpf, Long id) throws ClientNotFoundException, LoanNotFoundException {
        if (clientRepository.existsByCpf(cpf)) {
            if (loanRepository.existsByIdAndAndCpfClient(id, cpf)) {
                LoanEntity loanEntity = loanRepository.getReferenceById(id);

                return loanMapper.toDto(loanEntity);
            }
            throw new LoanNotFoundException(cpf, id);
        }
        throw new ClientNotFoundException(cpf);
    }

    public List<LoanDto> getAllLoans(String cpf) throws ClientNotFoundException {
        if (clientRepository.existsByCpf(cpf)) {
            ClientEntity clientEntity = clientRepository.findByCpf(cpf);

            return loanMapper.toDtoList(clientEntity.getLoans());
        }
        throw new ClientNotFoundException(cpf);
    }

    private LoanDto persistLoan(ClientEntity clientEntity, LoanDto loanDto) {
        LoanEntity loanEntity = loanMapper.toModel(loanDto, clientEntity.getCpf());

        loanEntity.setClient(clientEntity);
        loanEntity.updateEndValue();
        clientEntity.addLoan(loanEntity);
        loanRepository.save(loanEntity);
        return loanMapper.toDto(loanEntity);
    }

}
