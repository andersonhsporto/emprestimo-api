package dev.anderson.emprestimoapi.entities;

import dev.anderson.emprestimoapi.types.Membership;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
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

}
