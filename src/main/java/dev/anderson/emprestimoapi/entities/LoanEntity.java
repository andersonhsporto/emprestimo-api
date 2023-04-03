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
    private ClientEntity client;

}
