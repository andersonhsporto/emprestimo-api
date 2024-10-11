package dev.anderson.emprestimoapi.loan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    boolean existsByIdAndAndCpfClient(Long id, String cpf);

}
