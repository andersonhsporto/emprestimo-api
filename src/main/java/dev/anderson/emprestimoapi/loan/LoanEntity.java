package dev.anderson.emprestimoapi.loan;

import dev.anderson.emprestimoapi.client.ClientEntity;
import dev.anderson.emprestimoapi.common.types.Membership;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfClient;

    private BigDecimal startValue;

    private BigDecimal endValue;

    private Membership membership;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity client;

    public void updateEndValue() {
        endValue = membership.getEndValue(startValue, getNumberOfLoans());
    }

    private Integer getNumberOfLoans() {
        return client.getLoans().size();
    }
}
