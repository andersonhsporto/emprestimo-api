package dev.anderson.emprestimoapi.entities;

import dev.anderson.emprestimoapi.types.Membership;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF
    private String CPFClient;

    private BigDecimal startValue;

    private BigDecimal endValue;

    private Membership membership;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn
    private ClientEntity client;

    public void updateEndValue() {
        endValue = membership.getMembershipStatus(startValue, getNumberOfLoans());
    }

    private Integer getNumberOfLoans() {
        return client.getLoans().size();
    }

    @Override
    public String toString() {
        return "LoanEntity{" +
                "id=" + id +
                ", CPFClient='" + CPFClient + '\'' +
                ", startValue=" + startValue +
                ", endValue=" + endValue +
                ", membership=" + membership +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", client=" + client +
                '}';
    }
}
