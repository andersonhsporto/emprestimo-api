package dev.anderson.emprestimoapi.repositories;

import dev.anderson.emprestimoapi.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    boolean existsByIdAndAndCpfClient(Long id, String cpf);

}
