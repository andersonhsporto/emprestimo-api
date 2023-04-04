package dev.anderson.emprestimoapi.entities;

import dev.anderson.emprestimoapi.types.Membership;
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

    private String CPFClient;

    private BigDecimal startValue;

    private BigDecimal endValue;

    private Membership membership;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClientEntity client;

    public void updateEndValue() {
        endValue = membership.getEndValue(startValue, getNumberOfLoans());
    }

    private Integer getNumberOfLoans() {
        return client.getLoans().size();
    }
}
