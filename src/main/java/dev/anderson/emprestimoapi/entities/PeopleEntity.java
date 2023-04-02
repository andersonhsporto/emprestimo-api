package dev.anderson.emprestimoapi.entities;

import dev.anderson.emprestimoapi.types.Membership;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Entity
public class PeopleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CPF
    private String cpf;

    private String telephone;

    private String address;
    private BigDecimal salary;
    private Membership membership;
}
