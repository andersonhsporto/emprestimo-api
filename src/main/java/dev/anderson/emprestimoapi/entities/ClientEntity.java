package dev.anderson.emprestimoapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo 'nome' é obrigatório")
    private String name;

    @NotEmpty(message = "O campo 'CPF' é obrigatório")
    private String cpf;

    @NotEmpty(message = "O campo 'telefone' é obrigatório")
    private String telephone;

    @NotNull(message = "O campo 'salario' é obrigatório")
    private BigDecimal salary;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LoanEntity> loans;

    public boolean isEligibleForLoan(BigDecimal startValue) {
        BigDecimal loanTotal = getLoanValue();
        BigDecimal maxLoanValue = getClientLoanLimit();

        if (loanTotal.add(startValue).compareTo(maxLoanValue) > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addLoan(LoanEntity loan) {
        loans.add(loan);
    }

    public void removeLoan(LoanEntity loan) {
        loans.remove(loan);
    }

    private BigDecimal getLoanValue() {
        BigDecimal loanValue = BigDecimal.ZERO;

        if (loans.isEmpty()) {
            return loanValue;
        }
        for (LoanEntity loan : loans) {
            loanValue = loanValue.add(loan.getEndValue());
        }
        return loanValue;
    }

    private BigDecimal getClientLoanLimit() {
        return salary.multiply(BigDecimal.valueOf(10));
    }
}
