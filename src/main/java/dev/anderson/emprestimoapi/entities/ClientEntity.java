package dev.anderson.emprestimoapi.entities;

import dev.anderson.emprestimoapi.types.Membership;

import javax.persistence.*;

import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CPF
    private String cpf;

    private String telephone;
    private BigDecimal salary;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;
}
